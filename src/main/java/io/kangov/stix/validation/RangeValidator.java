package io.kangov.stix.validation;

import io.kangov.stix.validation.constraints.Range;
import io.micronaut.core.annotation.*;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


@Singleton
@Introspected
public class RangeValidator implements ConstraintValidator<Range, Object> {

    private static final Logger log = LoggerFactory.getLogger(RangeValidator.class);
    @Override
    public boolean isValid(
            @Nullable Object value,
            @NonNull AnnotationValue<Range> annotationMetadata,
            @NonNull ConstraintValidatorContext context) {

        log.debug("validating " + context.getRootBean());

        Object val = value;
        if (value instanceof Optional<?> o) {
            if (o.isEmpty()) return true;
            val = o.get();
        }

        context.messageTemplate("invalid value ("+val+"), must be between {min} and {max}"); // (1)

        if (val instanceof String s) {
            var min = annotationMetadata.intValue("min").orElse(0);
            var max = annotationMetadata.intValue("max").orElse(Integer.MAX_VALUE);
            return (s.length() >= min && s.length() <= max);
        } else if (val instanceof Integer v) {
            var min = annotationMetadata.intValue("min").orElse(Integer.MIN_VALUE);
            var max = annotationMetadata.intValue("max").orElse(Integer.MAX_VALUE);
            return (v >= min && v <= max);
        } else if (val instanceof Long v) {
            var min = annotationMetadata.longValue("min").orElse(Long.MIN_VALUE);
            var max = annotationMetadata.longValue("max").orElse(Long.MAX_VALUE);
            return (v >= min && v <= max);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
        }

    }
}