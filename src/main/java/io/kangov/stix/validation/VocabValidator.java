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
public class VocabValidator implements ConstraintValidator<Vocab, String> {

    private static final Logger log = LoggerFactory.getLogger(VocabValidator.class);

    @Override
    public boolean isValid(
            String value,
            @NonNull  AnnotationValue<Vocab> annotationMetadata,
            @NonNull  ConstraintValidatorContext context) {

        var vocab = annotationMetadata.get("value", Vocabs.Vocab.class)
            .orElseThrow(() -> new IllegalStateException("Need a vocabulary supplied"));

        log.debug("Validating [{}] against vocab [{}]", value, vocab.vocabName());

        return vocab.contains(value);

    }

}