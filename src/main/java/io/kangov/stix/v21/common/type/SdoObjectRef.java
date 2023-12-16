package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.v21.core.sdo.SdoObject;

import java.io.IOException;

@JsonSerialize(using = SdoObjectRef.Serializer.class)
public record SdoObjectRef(String id, SdoObject object) implements ObjectRef<SdoObject> {

    public static class Serializer extends StdSerializer<SdoObjectRef> {
        public Serializer() { this(null); }
        public Serializer(Class<SdoObjectRef> t) { super(t); }
        @Override
        public void serialize(SdoObjectRef value, JsonGenerator jgen, SerializerProvider _p) throws IOException {
            if (value != null) {
                jgen.writeString(value.id());
            }
        }
    }

    public static SdoObjectRef create(String id)                 { return new SdoObjectRef(id, null); }
    public static SdoObjectRef create(SdoObject obj)             { return new SdoObjectRef(obj.getId(), obj); }
    public static SdoObjectRef createSdoObjectRef(String id)     { return create(id); }
    public static SdoObjectRef createSdoObjectRef(SdoObject obj) { return create(obj); }

}
