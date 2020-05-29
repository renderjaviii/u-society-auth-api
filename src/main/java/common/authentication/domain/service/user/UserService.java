package common.authentication.domain.service.user;

import common.authentication.app.api.UserApi;
import common.authentication.app.rest.request.CreateUserRequest;
import common.authentication.domain.exception.GenericException;

public interface UserService {

    UserApi create(CreateUserRequest request) throws GenericException;

    UserApi get(String username) throws GenericException;

    UserApi get(String username, String documentNumber, String email, String phoneNumber) throws GenericException;

    void enableAccount(String username, String otpCode) throws GenericException;

}
