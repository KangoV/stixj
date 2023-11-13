package io.kangov.stixj.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartsWithValidator implements ConstraintValidator<StartsWith, String> {

    private static final String TEMPLATE = "StartsWith violation: string must start with value: %s, but provided value: %s ";
    private String prefix;

    @Override
    public void initialize(StartsWith startsWithConstraint) {
        prefix = startsWithConstraint.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        if (value.startsWith(prefix)){
            return true;
        } else{
            var violationMessage = String.format(TEMPLATE, prefix, value);
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
            return false;
        }
    }
}
