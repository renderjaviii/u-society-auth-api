package common.authentication.app.handler;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import common.authentication.app.api.ApiError;
import common.authentication.domain.exception.GenericException;
import common.authentication.domain.exception.UserValidationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String BAD_REQUEST = "BAD_REQUEST";
    private static final String BASIC_FORMAT = "%s %s";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String errorMessage = String.format(BASIC_FORMAT, "Malformed json request:", ex.getCause().getMessage());
        return new ResponseEntity<>(new ApiError(errorMessage, BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        StringJoiner s = new StringJoiner(", ");
        ex.getBindingResult().getAllErrors()
                .forEach(error -> s.add(String.format(BASIC_FORMAT, ((FieldError) error).getField(),
                        error.getDefaultMessage())));

        String errorMessage = String.format(BASIC_FORMAT, "Fields validation failed:", s.toString());
        return new ResponseEntity<>(new ApiError(errorMessage, BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( { ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> String.format("%s %s: %s",
                        violation.getRootBeanClass().getName(),
                        violation.getPropertyPath(),
                        violation.getMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ApiError(errors.toString(), "CONSTRAINT_VIOLATION_ERROR"),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(HttpServletRequest req,
                                                                 DataIntegrityViolationException e) {

        String errorMessage = String.format(BASIC_FORMAT, "SQL exception:", e.getCause().getMessage());
        return new ResponseEntity<>(new ApiError(errorMessage, "SQL_INTEGRITY_ERROR"), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), "MISSING_REQUEST_PARAMETER"), status);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiError> handleGeneric(GenericException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), ex.getErrorCode()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ApiError> handUserValidation(UserValidationException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), ex.getErrorCode()), HttpStatus.FORBIDDEN);
    }

}
