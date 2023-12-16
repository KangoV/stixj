package io.kangov.stix.validation;

import io.kangov.stix.validation.constraints.StartsWith;
import io.micronaut.core.annotation.*;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;


@Singleton
@Introspected
public class StartsWithValidator implements ConstraintValidator<StartsWith, Object> {

    @Override
    public boolean isValid(
            @Nullable Object value,
            @NonNull AnnotationValue<StartsWith> annotationMetadata,
            @NonNull ConstraintValidatorContext context) {

        var optPrefix = annotationMetadata.stringValue("value");
        context.messageTemplate("invalid value ({validatedValue}), must be prefixed with {value}"); // (1)
        return optPrefix.map(prefix -> value.toString().startsWith(prefix)).orElse(true);

    }
}