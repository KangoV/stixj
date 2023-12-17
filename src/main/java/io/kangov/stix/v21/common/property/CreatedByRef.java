package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.common.type.IdentityRef;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public interface CreatedByRef {

    /**
     * The created_by_ref property specifies the id property of the identity object that describes the entity that
     * created this object.
     * <p>
     * If this attribute is omitted, the source of this information is undefined. This may be used by object creators
     * who wish to remain anonymous.
     *
     * @return the identity reference
     */
    @JsonProperty("created_by_ref")
    @JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
    @Redactable(useMask = true, redactionMask = "object--__REDACTED__")

    IdentityRef getCreatedByRef();

}
