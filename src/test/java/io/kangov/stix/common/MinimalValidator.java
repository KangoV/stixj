package io.kangov.stix.common;

import io.micronaut.core.annotation.*;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Singleton
@Introspected
public class MinimalValidator implements ConstraintValidator<Minimal, Object> {

    private static final Logger log = LoggerFactory.getLogger(MinimalValidator.class);

    @Override
    public boolean isValid(
        @Nullable Object value,
        @NonNull AnnotationValue<Minimal> annotationMetadata,
        @NonNull ConstraintValidatorContext context) {

        log.debug("!MINIMAL!");

        log.debug("validating " + context.getRootBean());

        Object val = value;
        if (value instanceof Optional<?> o) {
            if (o.isEmpty()) return true;
            val = o.get();
        }

        context.messageTemplate("invalid value (" + val + "), must be between {min} and {max}"); // (1)

        if (val instanceof String s) {
            return true;
        } else if (val instanceof Iterable v) {
            return true;
        } else if (val instanceof Integer v) {
            return true;
        } else {
            throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
        }

    }
}
