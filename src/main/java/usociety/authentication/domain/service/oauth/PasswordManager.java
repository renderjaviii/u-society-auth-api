package usociety.authentication.domain.service.oauth;

import usociety.authentication.domain.exception.UserException;
import usociety.authentication.domain.model.User;

public interface PasswordManager {

    String encode(String rawPassword);

    User checkPassword(User user, String rawPassword);

    void validatePassword(User user, String oldPassword, String newPassword) throws UserException;

}
