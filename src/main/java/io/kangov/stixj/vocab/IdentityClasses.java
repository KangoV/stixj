package io.kangov.stixj.vocab;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public final class IdentityClasses implements StixVocabulary {

    public static final Set<String> TERMS = new HashSet<>(Arrays.asList(
            "individual", "group", "organization",
            "class", "unknown"));

    @JsonProperty("identity_classes_vocabulary")
    private Set<String> terms = TERMS;

    @Override
    public Set<String> getAllTerms() {
        return terms;
    }

}
