package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import io.kangov.stix.v21.meta.mdo.MarkingDefinition;

import java.io.IOException;

@JsonSerialize(using = MarkingDefinitionRef.Serializer.class)
public record MarkingDefinitionRef(
        String id,
        MarkingDefinition object) implements ObjectRef<MarkingDefinition> {

    public static class Serializer extends StdSerializer<MarkingDefinitionRef> {
        public Serializer() { this(null); }
        public Serializer(Class<MarkingDefinitionRef> t) { super(t); }
        @Override
        public void serialize(MarkingDefinitionRef value, JsonGenerator jgen, SerializerProvider _p) throws IOException {
            jgen.writeString(value.id());
        }
    }

    public static MarkingDefinitionRef create(String id) {
        return new MarkingDefinitionRef(id, null);
    }

    public static MarkingDefinitionRef create(MarkingDefinition object) {
        return new MarkingDefinitionRef(object.getId(), object);
    }

    public static MarkingDefinitionRef createMarkingDefinitionRef(String id) {
        return create(id);
    }

    public static MarkingDefinitionRef createMarkingDefinitionRef(MarkingDefinition obj)  {
        return create(obj);
    }

}
