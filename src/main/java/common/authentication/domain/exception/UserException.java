package common.authentication.domain.exception;

public class UserException extends GenericException {

    public static final String EMAIL_ALREADY_IN_USE = "EMAIL_ALREADY_IN_USE";
    public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, String errorCode) {
        super(message, errorCode);
    }

}
