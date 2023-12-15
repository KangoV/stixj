package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.kangov.stix.redaction.Redactable;
import io.micronaut.core.annotation.NonNull;
import org.immutables.value.Value;

import java.time.Instant;

/**
 *
 */
public interface Modified {

    @NonNull
    @JsonProperty("modified")
    @JsonPropertyDescription("""
        The modified property is only used by STIX Objects that support versioning and represents the time that this \
        particular version of the object was last modified.
        The object creator can use the time it deems most appropriate as the time this version of the object was \
        modified. The minimum precision MUST be milliseconds (three digits after the decimal place in seconds), but \
        MAY be more precise.
        If the created property is defined, then the value of the modified property for a given object version MUST be \
        later than or equal to the value of the created property.
        Object creators MUST set the modified property when creating a new version of an object if the created \
        property was set.
        """)
    @Value.Default
    @Redactable
    default Instant getModified(){
        return Instant.now();
    }
}
