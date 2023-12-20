package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.v21.bundle.Bundleable;

import java.io.IOException;

@SuppressWarnings("unused")
@JsonSerialize(using = BundleableRef.Serializer.class)
public record BundleableRef(String id, Bundleable object) implements ObjectRef<Bundleable> {

    public static class Serializer extends StdSerializer<BundleableRef> {
        public Serializer() { this(null); }
        public Serializer(Class<BundleableRef> t) { super(t); }
        @Override
        public void serialize(BundleableRef value, JsonGenerator jgen, SerializerProvider _p) throws IOException {
            if (value != null) {
                jgen.writeString(value.id());
            }
        }
    }

    public static BundleableRef create(String id)                  { return new BundleableRef(id, null); }
    public static BundleableRef create(Bundleable obj)             { return new BundleableRef(obj.getId(), obj); }
    public static BundleableRef createSdoObjectRef(String id)      { return create(id); }
    public static BundleableRef createSdoObjectRef(Bundleable obj) { return create(obj); }

}
