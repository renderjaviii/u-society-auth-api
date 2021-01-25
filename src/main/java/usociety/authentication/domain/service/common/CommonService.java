package usociety.authentication.domain.service.common;

import usociety.authentication.domain.exception.GenericException;
import usociety.authentication.domain.model.User;

public interface CommonService {

    User getUser(Long userId);

    User getUser(String username) throws GenericException;

}
