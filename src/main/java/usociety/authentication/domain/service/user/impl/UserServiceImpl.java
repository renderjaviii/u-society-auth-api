package usociety.authentication.domain.service.user.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static usociety.authentication.domain.exception.UserException.EMAIL_ALREADY_IN_USE;
import static usociety.authentication.domain.exception.UserException.USER_ALREADY_EXISTS;
import static usociety.authentication.domain.exception.UserException.USER_NOT_FOUND;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usociety.authentication.app.api.UserApi;
import usociety.authentication.app.rest.request.ChangePasswordRequest;
import usociety.authentication.app.rest.request.CreateUserRequest;
import usociety.authentication.domain.converter.Converter;
import usociety.authentication.domain.exception.GenericException;
import usociety.authentication.domain.exception.UserException;
import usociety.authentication.domain.model.Role;
import usociety.authentication.domain.model.User;
import usociety.authentication.domain.repository.RoleRepository;
import usociety.authentication.domain.service.common.CommonServiceImpl;
import usociety.authentication.domain.service.oauth.PasswordManager;
import usociety.authentication.domain.service.user.UserService;

@Service
public class UserServiceImpl extends CommonServiceImpl implements UserService {

    private static final String USER_ALREADY_EXISTS_FORMAT = "Username: %s or DocumentNumber: %s already registered.";
    private static final String EMAIL_ALREADY_IN_USE_FORMAT = "Email: %s is already in use.";
    private static final String USER_NOT_FOUND_FORMAT = "User with username: %s not found.";
    private static final String ROLE_ERROR_FORMAT = "The role: %s not exists.";

    private final RoleRepository roleRepository;
    private final PasswordManager passwordManager;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository,
                           PasswordManager passwordManager) {
        this.roleRepository = roleRepository;
        this.passwordManager = passwordManager;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserApi create(CreateUserRequest request) throws UserException {
        validateUser(request);

        User savedUser = userRepository.save(User.newBuilder()
                .password(passwordManager.encode(request.getPassword()))
                .username(request.getUsername())
                .name(request.getName())
                .createdAt(LocalDate.now(clock))
                .email(request.getEmail())
                .photo(request.getPhoto())
                .role(buildUserRoles(request))
                .build());
        return Converter.user(savedUser);
    }

    @Override
    public UserApi get(String username) throws UserException {
        return Converter.user(userRepository.findByUsernameAndAccountLocked(username, FALSE)
                .orElseThrow(() -> new UserException(String.format(USER_NOT_FOUND_FORMAT, username), USER_NOT_FOUND)));
    }

    @Override
    public UserApi get(String username, String documentNumber, String email, String phoneNumber)
            throws UserException {
        return Converter.user(userRepository
                .findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new UserException("No user match using these filters.", USER_NOT_FOUND)));
    }

    @Override
    public void enableAccount(String username) throws GenericException {
        User user = getUser(username);
        user.setEmailVerified(TRUE);
        userRepository.save(user);
    }

    @Override
    public void delete(String username) throws UserException {
        User user = userRepository.findByUsernameAndAccountLocked(username, FALSE)
                .orElseThrow(() -> new UserException(String.format(USER_NOT_FOUND_FORMAT, username), USER_NOT_FOUND));
        user.setAccountLocked(TRUE);
        userRepository.save(user);
    }

    @Override
    public List<UserApi> getAll() {
        return userRepository.findAll().stream()
                .map(Converter::user)
                .collect(Collectors.toList());
    }

    @Override
    public void changePassword(String username, ChangePasswordRequest request) throws GenericException {
        passwordManager.validatePassword(getUser(username), request.getOldPassword(), request.getNewPassword());
    }

    private void validateUser(CreateUserRequest request) throws UserException {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserException(
                    String.format(USER_ALREADY_EXISTS_FORMAT, request.getUsername(), request.getDocumentNumber()),
                    USER_ALREADY_EXISTS);
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserException(String.format(EMAIL_ALREADY_IN_USE_FORMAT, request.getEmail()),
                    EMAIL_ALREADY_IN_USE);
        }
    }

    private Role buildUserRoles(CreateUserRequest request) throws UserException {
        String role = request.getUserRole().replaceFirst("", "ROLE_");
        return roleRepository.findByName(role)
                .orElseThrow(() -> new UserException(String.format(ROLE_ERROR_FORMAT, request.getUserRole()),
                        "INVALID_ROLE"));
    }

}
