package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kangov.stix.json.converters.dehydrated.DomainObjectOptionalConverter;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.core.sdo.objects.Identity;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public interface CreatedByRef {

    @JsonProperty("created_by_ref")
    @JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
    @JsonPropertyDescription("""
        The created_by_ref property specifies the id property of the identity object that describes the entity that \
        created this object.
        If this attribute is omitted, the source of this information is undefined. This may be used by object creators \
        who wish to remain anonymous.
        """)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectOptionalConverter.class)
    @Redactable(useMask = true, redactionMask = "identity--__REDACTED__")
    Optional<Identity> getCreatedByRef();

}
