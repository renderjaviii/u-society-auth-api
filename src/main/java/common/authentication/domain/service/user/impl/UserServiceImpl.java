package common.authentication.domain.service.user.impl;

import static java.lang.Boolean.TRUE;

import java.time.Clock;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import common.authentication.app.api.UserApi;
import common.authentication.app.rest.request.CreateUserRequest;
import common.authentication.domain.converter.Converter;
import common.authentication.domain.exception.GenericException;
import common.authentication.domain.model.Role;
import common.authentication.domain.model.User;
import common.authentication.domain.repository.RoleRepository;
import common.authentication.domain.repository.UserRepository;
import common.authentication.domain.service.common.CommonServiceImpl;
import common.authentication.domain.service.oauth.PasswordManager;
import common.authentication.domain.service.user.UserService;

@Service
@EnableConfigurationProperties
public class UserServiceImpl extends CommonServiceImpl implements UserService {

    private static final String ROLE_ERROR_FORMAT = "The role: %s not exists.";
    private static final String USER_ALREADY_EXISTS_FORMAT = "UserName: %s or DocumentNumber: %s already registered.";
    private static final String EMAIL_ALREADY_IN_USE = "Email: %s is already in use.";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordManager passwordManager;
    private final Clock clock;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordManager passwordManager,
                           Clock clock) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordManager = passwordManager;
        this.clock = clock;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserApi create(CreateUserRequest request) throws GenericException {
        validateUser(request);

        User savedUser = userRepository.save(User.newBuilder()
                .password(passwordManager.encode(request.getPassword()))
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .createdAt(LocalDate.now(clock))
                .documentNumber(request.getDocumentNumber())
                .email(request.getEmail())
                .role(buildUserRoles(request))
                .build());

        return Converter.user(savedUser);
    }

    @Override
    public UserApi get(String username) throws GenericException {
        return Converter.user(userRepository.findByUsername(username)
                .orElseThrow(() -> new GenericException("User with username: %s not found.", "USER_NOT_FOUND")));
    }

    @Override
    public UserApi get(String username, String documentNumber, String email, String phoneNumber)
            throws GenericException {
        return Converter.user(userRepository
                .findByUsernameOrDocumentNumberOrEmailOrPhoneNumber(username, documentNumber, email, phoneNumber)
                .orElseThrow(() -> new GenericException("No user match using these filters.", "USER_NOT_FOUND")));
    }

    @Override
    public void enableAccount(String username) throws GenericException {
        User user = getUser(username);
        user.setEmailVerified(TRUE);
        userRepository.save(user);
    }

    private void validateUser(CreateUserRequest request) throws GenericException {
        if (userRepository.findByUsernameOrDocumentNumber(request.getUsername(), request.getDocumentNumber())
                .isPresent()) {
            throw new GenericException(
                    String.format(USER_ALREADY_EXISTS_FORMAT, request.getUsername(), request.getDocumentNumber()),
                    "USER_ALREADY_EXISTS");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new GenericException(String.format(EMAIL_ALREADY_IN_USE, request.getEmail()), "EMAIL_ALREADY_IN_USE");
        }
    }

    private Role buildUserRoles(CreateUserRequest request) throws GenericException {
        String role = request.getUserRole().replaceFirst("", "ROLE_");
        return roleRepository.findByName(role)
                .orElseThrow(() -> new GenericException(String.format(ROLE_ERROR_FORMAT, request.getUserRole()),
                        "INVALID_ROLE"));
    }

}
