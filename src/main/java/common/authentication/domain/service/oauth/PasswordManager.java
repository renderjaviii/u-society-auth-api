package common.authentication.domain.service.oauth;

import common.authentication.domain.exception.UserException;
import common.authentication.domain.model.User;

public interface PasswordManager {

    String encode(String rawPassword);

    User checkPassword(User user, String rawPassword);

    void validatePassword(User user, String oldPassword, String newPassword) throws UserException;

}
