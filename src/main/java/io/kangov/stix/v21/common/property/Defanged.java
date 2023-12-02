package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.redaction.Redactable;
import io.micronaut.core.annotation.NonNull;
import org.immutables.value.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * This property defines whether the data contained within the object has been defanged.
 * The default value for this property is false.
 * This property MUST NOT be used on any STIX Objects other than SCOs.
 */
public interface Defanged {

    @NonNull
    @JsonProperty("revoked")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("""
        This property defines whether or not the data contained within the object has been defanged.
        The default value for this property is false.
        This property MUST NOT be used on any STIX Objects other than SCOs.
        """)
    @Value.Default
    @Redactable
    default Boolean getRevoked(){
        return Boolean.FALSE;
    }

}
