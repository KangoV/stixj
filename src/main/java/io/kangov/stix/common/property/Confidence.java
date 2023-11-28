package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.validation.constraints.Range;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.Min;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public interface Confidence {

    @NonNull
    @JsonProperty("confidence")
    @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("""
            The confidence property identifies the confidence that the creator has in the correctness of their data. \
            The confidence value MUST be a number in the range of 0-100.
            Appendix A contains a table of normative mappings to other confidence scales that MUST be used when \
            presenting the confidence value in one of those scales.
            If the confidence property is not present, then the confidence of the content is unspecified.
            """)
    @Redactable(useMask = true)


    Optional<Integer> getConfidence();

}
