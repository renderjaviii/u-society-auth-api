package usociety.authentication.domain.service.user;

import java.util.List;

import usociety.authentication.app.api.UserApi;
import usociety.authentication.app.rest.request.ChangePasswordRequest;
import usociety.authentication.app.rest.request.CreateUserRequest;
import usociety.authentication.domain.exception.GenericException;
import usociety.authentication.domain.exception.UserException;

public interface UserService {

    UserApi create(CreateUserRequest request) throws UserException;

    void update(UserApi request) throws UserException;

    UserApi get(String username) throws UserException;

    UserApi get(Long id, String username, String email) throws UserException;

    void enableAccount(String username) throws GenericException;

    void delete(String username) throws UserException;

    List<UserApi> getAll();

    void changePassword(String username, ChangePasswordRequest request) throws GenericException;

}
