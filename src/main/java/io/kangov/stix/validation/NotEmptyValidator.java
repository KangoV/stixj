package io.kangov.stix.validation;

import io.kangov.stix.validation.constraints.StartsWith;
import io.micronaut.core.annotation.*;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;

import java.util.Collection;


@Singleton
@Introspected
public class NotEmptyValidator implements ConstraintValidator<StartsWith, Object> {

    @Override
    public boolean isValid(
            @Nullable Object value,
            @NonNull AnnotationValue<StartsWith> annotationMetadata,
            @NonNull ConstraintValidatorContext context) {

        context.messageTemplate("Not Empty"); // (1)

        if (value instanceof Collection v) {
            return !v.isEmpty();
        } else if (value instanceof String v) {
            return !v.isBlank();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
        }

    }
}