package io.kangov.stixj.constraints.vocab;

import io.kangov.stixj.vocab.StixVocabulary;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StixVocabValidatorCollection implements ConstraintValidator<Vocab, Set<String>> {

    private Class<? extends StixVocabulary> vocabulary;
    private Set<String> terms;

    @Override
    public void initialize(Vocab vocabConstraint) {
        try {
            terms = vocabConstraint.value().newInstance().getAllTerms();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isValid(Set<String> vocab, ConstraintValidatorContext cxt) {
        boolean evalContains = terms.containsAll(vocab);
        if (!evalContains){
            cxt.disableDefaultConstraintViolation();

            Set<String> difference = new HashSet<>(vocab)
                .stream()
                .filter(e -> !terms.contains(e))
                .collect(Collectors.toSet());

            String violationMessage = "Items: " + difference.toString() + " are not found in class " + vocabulary.getCanonicalName();
            cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
        }
        return evalContains;
    }
}
