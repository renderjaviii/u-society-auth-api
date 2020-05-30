package common.authentication.domain.service.user;

import common.authentication.app.api.UserApi;
import common.authentication.app.rest.request.CreateUserRequest;
import common.authentication.domain.exception.GenericException;
import common.authentication.domain.exception.UserException;

public interface UserService {

    UserApi create(CreateUserRequest request) throws UserException;

    UserApi get(String username) throws UserException;

    UserApi get(String username, String documentNumber, String email, String phoneNumber) throws UserException;

    void enableAccount(String username) throws GenericException;

    void delete(String username) throws UserException;

}
