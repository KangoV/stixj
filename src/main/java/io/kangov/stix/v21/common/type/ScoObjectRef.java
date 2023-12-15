package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.v21.core.sco.ScoObject;

import java.io.IOException;

@JsonSerialize(using = ScoObjectRef.Serializer.class)
public record ScoObjectRef(String id, ScoObject object) implements ObjectRef<ScoObject> {

    public static class Serializer extends StdSerializer<ScoObjectRef> {
        public Serializer() { this(null); }
        public Serializer(Class<ScoObjectRef> t) { super(t); }
        @Override
        public void serialize(ScoObjectRef value, JsonGenerator jgen, SerializerProvider _p) throws IOException, JsonProcessingException {
            if (value != null) {
                jgen.writeString(value.id());
            }
        }
    }

    public static ScoObjectRef create(String id)                 { return new ScoObjectRef(id, null); }
    public static ScoObjectRef create(ScoObject obj)             { return new ScoObjectRef(obj.getId(), obj); }
    public static ScoObjectRef createScoObjectRef(String id)     { return create(id); }
    public static ScoObjectRef createScoObjectRef(ScoObject obj) { return create(obj); }

}
