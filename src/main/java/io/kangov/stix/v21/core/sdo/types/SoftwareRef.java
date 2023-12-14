package io.kangov.stix.v21.core.sdo.types;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.v21.core.sco.objects.Software;

import java.io.IOException;

@JsonSerialize(using = SoftwareRef.Serializer.class)
public record SoftwareRef(String id, Software object) {

    public static class Serializer extends StdSerializer<SoftwareRef> {
        public Serializer() { this(null); }
        public Serializer(Class<SoftwareRef> t) { super(t); }
        @Override
        public void serialize(SoftwareRef value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value != null) {
                jgen.writeString(value.id());
            }
        }
    }

    public static SoftwareRef create(String id)               { return new SoftwareRef(id, null); }
    public static SoftwareRef create(Software identity)       { return new SoftwareRef(identity.getId(), identity); }
    public static SoftwareRef createIdentityRef(String id)    { return create(id); }
    public static SoftwareRef createIdentityRef(Software obj) { return create(obj); }

    public String id() {
        return object() != null ? object().getId() : id();
    }

}
