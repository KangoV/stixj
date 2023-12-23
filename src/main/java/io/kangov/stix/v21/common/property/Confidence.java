package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kangov.stix.redaction.Redactable;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public interface Confidence {

    /**
     * The confidence property identifies the confidence that the creator has in the correctness of their data. The
     * confidence value MUST be a number in the range of 0-100.
     * <p>
     * Appendix A contains a table of normative mappings to other confidence scales that MUST be used when presenting
     * the confidence value in one of those scales.
     * <p>
     * If the confidence property is not present, then the confidence of the content is unspecified.
     *
     * @return the confidence that the creator has in the correctness of their data
     */
    @NonNull
    @JsonProperty("confidence")
    @JsonInclude(NON_EMPTY)
    @Redactable(useMask = true)
    Optional<@Size(max=100) Integer> getConfidence();

}
