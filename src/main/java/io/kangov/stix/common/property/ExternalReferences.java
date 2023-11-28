package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.common.type.ExternalReference;
import io.kangov.stix.redaction.Redactable;
import io.micronaut.core.annotation.NonNull;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public interface ExternalReferences {

    @NonNull
    @JsonProperty("external_references")
    @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("""
        The external_references property specifies a list of external references which refers to non-STIX information. \
        This property is used to provide one or more URLs, descriptions, or IDs to records in other systems.
        """)
    @Redactable
    Set<ExternalReference> getExternalReferences();

}
