package io.kangov.stix.validation;

import io.kangov.stix.v21.enums.Vocabs;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.*;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Introspected
public class VocabValidator implements ConstraintValidator<Vocab, String> {

    private static final Logger log = LoggerFactory.getLogger(VocabValidator.class);

    @Override
    public boolean isValid(
            String value,
            @NonNull  AnnotationValue<Vocab> annotationMetadata,
            @NonNull  ConstraintValidatorContext context) {

        annotationMetadata.get("value", Vocabs.Vocab.class).ifPresentOrElse(
            v -> {
                // validate here
            },
            () -> {
                log.warn("No vocab set in [value={}, annotationMetadata={}, context={}]", value, annotationMetadata, context);
            }
        );

        return true;

    }

}