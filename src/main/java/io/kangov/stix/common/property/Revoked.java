package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.redaction.Redactable;
import io.micronaut.core.annotation.NonNull;
import org.immutables.value.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The revoked property is only used by STIX Objects that support versioning and indicates whether the object has been revoked.
 * Revoked objects are no longer considered valid by the object creator. Revoking an object is permanent; future versions of the object with this id MUST NOT be created.
 * The default value of this property is false.
 */
public interface Revoked {

    @NonNull
    @JsonProperty("revoked")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("""
        The revoked property is only used by STIX Objects that support versioning and indicates whether the object \
        has been revoked.
        Revoked objects are no longer considered valid by the object creator. Revoking an object is permanent; future \
        versions of the object with this id MUST NOT be created.
        The default value of this property is false.
        """)
    @Value.Default
    @Redactable
    default Boolean getRevoked(){
        return Boolean.FALSE;
    }

}
