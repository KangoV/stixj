package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.kangov.stix.redaction.Redactable;
import io.micronaut.core.annotation.NonNull;
import org.immutables.value.Value;

import java.time.Instant;

public interface Created {

    @NonNull
    @JsonProperty("created")
    @JsonPropertyDescription("""
        The created property represents the time at which the object was originally created.
        The object creator can use the time it deems most appropriate as the time the object was created. The minimum \
        precision MUST be milliseconds (three digits after the decimal place in seconds), but MAY be more precise.
        The created property MUST NOT be changed when creating a new version of the object.
        """)
    @Value.Default
    @Redactable(useMask = true)
    default Instant getCreated(){
        return Instant.now();
    }

}
