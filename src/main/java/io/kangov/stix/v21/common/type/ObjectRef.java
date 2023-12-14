package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.v21.bundle.Bundleable;

import java.io.IOException;

@JsonSerialize(using = ObjectRef.Serializer.class)
public record ObjectRef(String id, Bundleable object) {

    public static class Serializer extends StdSerializer<ObjectRef> {
        public Serializer() { this(null); }
        public Serializer(Class<ObjectRef> t) { super(t); }
        @Override
        public void serialize(ObjectRef value, JsonGenerator jgen, SerializerProvider _p) throws IOException, JsonProcessingException {
            if (value != null) {
                jgen.writeString(value.id());
            }
        }
    }

    public static ObjectRef create(String id)                  { return new ObjectRef(id, null); }
    public static ObjectRef create(Bundleable obj)             { return new ObjectRef(obj.getId(), obj); }
    public static ObjectRef createSdoObjectRef(String id)      { return create(id); }
    public static ObjectRef createSdoObjectRef(Bundleable obj) { return create(obj); }

    public String id() {
        return object() != null ? object().getId() : id();
    }

}
