package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.v21.core.sdo.objects.Identity;

import java.io.IOException;

@JsonSerialize(using = IdentityRef.Serializer.class)
public record IdentityRef(
        String id,
        Identity object) implements ObjectRef<Identity> {

    public static class Serializer extends StdSerializer<IdentityRef> {
        public Serializer() { this(null); }
        public Serializer(Class<IdentityRef> t) { super(t); }
        @Override
        public void serialize(IdentityRef value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (value != null) {
                jgen.writeString(value.id());
            }
        }
    }

    public static IdentityRef create(String id)               { return new IdentityRef(id, null); }
    public static IdentityRef create(Identity identity)       { return new IdentityRef(identity.getId(), identity); }
    public static IdentityRef createIdentityRef(String id)    { return create(id); }
    public static IdentityRef createIdentityRef(Identity obj) { return create(obj); }

}
