package io.kangov.stix.validation;

import io.kangov.stix.validation.constraints.StartsWith;
import io.micronaut.context.annotation.Factory;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import jakarta.inject.Singleton;

@Factory
public class ValidatorFactory {

    @Singleton
    ConstraintValidator<StartsWith, Object> startsWithValidator() {
        return (value, annotationMetadata, context) -> {
            var prefix = annotationMetadata.stringValue().get();
            return value == null || value.toString().startsWith(prefix);
        };
    }

}