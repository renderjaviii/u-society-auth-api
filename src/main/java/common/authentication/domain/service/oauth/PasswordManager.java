package common.authentication.domain.service.oauth;

import org.springframework.security.authentication.AccountStatusException;

import common.authentication.domain.model.User;

public interface PasswordManager {

    String encode(String rawPassword);

    User checkPassword(User user, String rawPassword) throws AccountStatusException;

}
