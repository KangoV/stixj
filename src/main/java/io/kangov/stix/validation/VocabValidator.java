package io.kangov.stix.validation;

import io.kangov.stix.v21.enums.Vocabs;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.*;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


@Singleton
@Introspected
public class VocabValidator implements ConstraintValidator<Vocab, Object> {

    private static final Logger log = LoggerFactory.getLogger(VocabValidator.class);

    private Vocabs.Vocab vocab;

    @Override
    public boolean isValid(
            @Nullable Object value,
            @NonNull AnnotationValue<Vocab> annotationMetadata,
            @NonNull ConstraintValidatorContext context) {

        log.debug("!VOCAB!");

        log.debug("validating " + context.getRootBean());

        Object val = value;
        if (value instanceof Optional<?> o) {
            if (o.isEmpty()) return true;
            val = o.get();
        }

        context.messageTemplate("invalid value ("+val+"), must be between {min} and {max}"); // (1)

        if (val instanceof String string) {

            return vocab.contains(string);

        } else if (val instanceof Iterable v) {

            return true;

        } else {
            throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
        }

    }

    /**
     * Initializes the validator in preparation for {@link #isValid(Object, ConstraintValidatorContext)} calls. The
     * constraint annotation for a given constraint declaration is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(Vocab constraintAnnotation) {
        this.vocab = constraintAnnotation.value();
    }
}