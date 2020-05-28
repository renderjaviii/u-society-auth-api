package common.authentication.domain.service.user;

import org.springframework.security.core.Authentication;

import common.authentication.app.api.UserApi;
import common.authentication.app.rest.request.CreateUserRequest;
import common.authentication.app.rest.response.CreateUserResponse;
import common.authentication.domain.exception.GenericException;

public interface UserService {

    CreateUserResponse create(CreateUserRequest request) throws GenericException;

    Authentication getTokenInfo();

    UserApi get(String username) throws GenericException;

    void enableAccount(String username, String otpCode) throws GenericException;

}
