
package common.authentication.app.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import common.authentication.app.rest.request.CreateUserRequest;

public class CreateUserRequestValidator implements ConstraintValidator<CreateUserRequestConstraint, CreateUserRequest> {

    @Override
    public void initialize(CreateUserRequestConstraint constraintAnnotation) {
        //Implementation is not necessary
    }

    @Override
    public boolean isValid(CreateUserRequest request, ConstraintValidatorContext context) {
        return true;
    }

}
