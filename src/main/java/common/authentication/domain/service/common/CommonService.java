package common.authentication.domain.service.common;

import common.authentication.domain.exception.GenericException;
import common.authentication.domain.model.User;

public interface CommonService {

    User getUser(Long userId);

    User getUser(String username) throws GenericException;

}
