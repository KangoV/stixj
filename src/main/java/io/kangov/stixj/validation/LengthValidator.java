package io.kangov.stixj.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthValidator implements ConstraintValidator<Length, CharSequence> {

    private int min;
    private int max;

    @Override
    public void initialize(Length parameters) {
        min = parameters.min();
        max = parameters.max();
        validateParameters();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext cxt) {
        if ( value == null ) return true;
        int length = value.length();
        return length >= min && length <= max;
    }

    private void validateParameters() {
        if ( min < 0 ) throw new IllegalStateException("Min cannot be negative");
        if ( max < 0 ) throw new IllegalStateException("Max cannot be negative");
        if ( max < min ) throw new IllegalStateException("Length cannot be negative");
    }

}
