package io.kangov.stixj.vocab;

import javax.swing.plaf.synth.Region;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public sealed interface StixVocabulary permits
        IdentityClasses
    {

    /**
     * Returns the default terms for this vocabulary
     *
     * @return the default terms for this vocabulary
     */
    Set<String> getAllTerms();

    /**
     * Returns the default terms combined with those provided
     *
     * @param terms The extra terms to be combined with the default terms for this vocabulary
     * @return the default terms combined with those provided
     */
    @SuppressWarnings("unused")
    default Set<String> getAllTermsWithAdditional(String[] terms) {
        return Stream.concat(getAllTerms().stream(), Arrays.stream(terms))
            .collect(Collectors.toCollection(HashSet::new));
    }

    static Set<String> asSet(Class<? extends StixVocabulary> type) {
        try {
            var field = type.getField("TERMS");
            var terms = field.get(null);
            return (Set<String>) terms;
        } catch (Exception e) {
            throw new IllegalStateException("Static field TERMS not found on class " + type.getCanonicalName());
        }
    }

    static List<String> asList(Class<? extends StixVocabulary> type) {
        return new ArrayList<>(asSet(type));
    }

}
