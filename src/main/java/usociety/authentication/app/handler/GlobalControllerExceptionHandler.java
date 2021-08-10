package usociety.authentication.app.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import usociety.authentication.app.api.ApiError;
import usociety.authentication.domain.exception.GenericException;

@SuppressWarnings("unnused")
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INTERNAL_ERROR_MESSAGE = "Internal server error";
    private static final String INTERNAL_ERROR = "INTERNAL_SERVER_ERROR";
    private static final String BAD_REQUEST_ERROR = "BAD_REQUEST";
    private static final String FIELD_ERROR_FORMAT = "%s %s: %s";
    private static final String BASIC_ERROR_FORMAT = "%s %s";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String errorMessage = String.format(BASIC_ERROR_FORMAT, "Malformed json body: ", ex.getLocalizedMessage());
        return new ResponseEntity<>(new ApiError(errorMessage, BAD_REQUEST_ERROR), status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter
            (MissingServletRequestParameterException ex,
             HttpHeaders headers,
             HttpStatus status,
             WebRequest request) {
        String errorMessage = String.format(BASIC_ERROR_FORMAT, "Missing request param:", ex.getMessage());
        return new ResponseEntity<>(new ApiError(errorMessage, BAD_REQUEST_ERROR), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String errorMessages = getFieldErrorMessages(ex);
        String errorMessage = String.format(BASIC_ERROR_FORMAT, "Fields validation failed:", errorMessages);
        return new ResponseEntity<>(new ApiError(errorMessage, BAD_REQUEST_ERROR), status);
    }

    @ExceptionHandler( { ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolationError(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> String.format(FIELD_ERROR_FORMAT,
                        violation.getRootBeanClass().getName(),
                        violation.getPropertyPath(),
                        violation.getMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ApiError(errors.toString(), BAD_REQUEST_ERROR), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handInternalError(Exception ex) {
        logger.error(ex);
        return new ResponseEntity<>(new ApiError(INTERNAL_ERROR_MESSAGE, INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationError
            (HttpServletRequest req,
             DataIntegrityViolationException e) {
        logger.error(String.format(BASIC_ERROR_FORMAT, "SQL exception: ", e.getCause().getMessage()));
        return new ResponseEntity<>(new ApiError(INTERNAL_ERROR_MESSAGE, INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiError> handleGenericException(GenericException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), ex.getErrorCode()), CONFLICT);
    }

    private String getFieldErrorMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::buildFieldErrorMessage)
                .collect(Collectors.joining(", "));
    }

    private String buildFieldErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            return String.format(BASIC_ERROR_FORMAT, ((FieldError) error).getField(), error.getDefaultMessage());
        }
        return error.getDefaultMessage();
    }

}
