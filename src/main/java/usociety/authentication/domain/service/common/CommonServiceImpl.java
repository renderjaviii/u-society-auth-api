package usociety.authentication.domain.service.common;

import static usociety.authentication.domain.exception.UserException.USER_NOT_FOUND;

import java.time.Clock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usociety.authentication.domain.exception.GenericException;
import usociety.authentication.domain.model.User;
import usociety.authentication.domain.repository.UserRepository;

@Service
public abstract class CommonServiceImpl implements CommonService {

    private static final String USER_NOT_FOUND_FORMAT = "Username: %s not found.";

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected Clock clock;

    protected CommonServiceImpl() {
        super();
    }

    public User getUser(Long userId) {
        return userRepository.getOne(userId);
    }

    public User getUser(String username) throws GenericException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    String errorMessage = String.format(USER_NOT_FOUND_FORMAT, username);
                    return new GenericException(errorMessage, USER_NOT_FOUND);
                });
    }

}
