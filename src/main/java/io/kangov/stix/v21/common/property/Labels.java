package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.validation.constraints.Range;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 *
 */
public interface Labels {

    @NonNull
    @JsonProperty("labels") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<@Size(min=3)  String> getLabels();

}
