package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.common.type.IdentityRef;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public interface CreatedByRef {

    class IdentityRefSerializer extends StdSerializer<IdentityRef> {
        public IdentityRefSerializer() {
            this(null);
        }
        public IdentityRefSerializer(Class<IdentityRef> t) {
            super(t);
        }
        @Override
        public void serialize(IdentityRef value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value != null) {
                var id = value.identity() != null ? value.identity().getId() : value.id();
                jgen.writeString(id);
            }
        }
    }

    @JsonProperty("created_by_ref")
    @JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
    @JsonPropertyDescription("""
        The created_by_ref property specifies the id property of the identity object that describes the entity that \
        created this object.
        If this attribute is omitted, the source of this information is undefined. This may be used by object creators \
        who wish to remain anonymous.
        """)
    @JsonSerialize(using=IdentityRefSerializer.class)
    @Redactable(useMask = true, redactionMask = "identity--__REDACTED__")
    IdentityRef getCreatedByRef();

}
