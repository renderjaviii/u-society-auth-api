package common.authentication.domain.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.authentication.domain.exception.GenericException;
import common.authentication.domain.model.User;
import common.authentication.domain.repository.UserRepository;

@Service
public abstract class CommonServiceImpl implements CommonService {

    private static final String USER_NOT_FOUND_FORMAT = "Username: %s not found.";

    @Autowired
    private UserRepository userRepository;

    public CommonServiceImpl() {
        super();
    }

    public User getUser(Long userId) {
        return userRepository.getOne(userId);
    }

    public User getUser(String username) throws GenericException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new GenericException(String.format(USER_NOT_FOUND_FORMAT, username), "USER NOT FOUND"));
    }

}
