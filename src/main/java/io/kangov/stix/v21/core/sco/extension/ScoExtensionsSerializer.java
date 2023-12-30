package io.kangov.stix.v21.core.sco.extension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ScoExtensionsSerializer extends StdSerializer<ScoExtensions> {

    public ScoExtensionsSerializer() {this(null);}

    public ScoExtensionsSerializer(Class<ScoExtensions> t) {super(t);}

    @Override
    public void serialize(ScoExtensions value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            gen.writeStartObject();
            for (var entry : value.getExtensions()) {
                gen.writeFieldName(entry.getType());
                gen.writePOJO(entry);
            }
            gen.writeEndObject();
        }
    }
}
