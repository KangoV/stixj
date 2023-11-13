package io.kangov.stixj.constraints.vocab;

import io.kangov.stixj.vocab.StixVocabulary;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;
import java.util.stream.Collectors;

public class StixVocabValidatorOptionalString implements ConstraintValidator<Vocab, Optional<String>> {

    private Class<? extends StixVocabulary> vocabulary;

    @Override
    public void initialize(Vocab vocabConstraint) {
        vocabulary = vocabConstraint.value();
    }

    @Override
    public boolean isValid(Optional<String> vocab, ConstraintValidatorContext cxt) {
        if (vocab.isPresent()) {
            try {
                Set<String> vocabTerms = vocabulary.newInstance().getAllTerms();
                boolean evalContains = vocabTerms.contains(vocab.get());
                if (!evalContains) {

                    Set<String> difference = new HashSet<>(Arrays.asList(vocab.get()))
                        .stream()
                        .filter(e -> !vocabTerms.contains(e))
                        .collect(Collectors.toSet());

                    cxt.disableDefaultConstraintViolation();
                    String violationMessage = "Item: " + difference.toString() + " is not found in class " + vocabulary.getCanonicalName();
                    cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                }

                return evalContains;

            } catch (InstantiationException e) {
                cxt.disableDefaultConstraintViolation();
                String violationMessage = "InstantiationException from " + vocabulary.getSimpleName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;

            } catch (IllegalAccessException e) {
                cxt.disableDefaultConstraintViolation();
                String violationMessage = "IllegalAccessException from " + vocabulary.getSimpleName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }
}
