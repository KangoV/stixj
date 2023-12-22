package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.kangov.stix.Parser;
import io.kangov.stix.v21.bundle.Bundleable;

import java.io.IOException;
import java.util.*;

@JsonSerialize(using = ObjectRef.Serializer.class)
@JsonDeserialize(using = ObjectRef.Deserialiser.class)
public class ObjectRef<T extends Bundleable> {

    public static class Serializer extends StdSerializer<ObjectRef<?>> {

        public Serializer() { this(null); }
        public Serializer(Class<ObjectRef<?>> t) { super(t); }

        @Override
        public void serialize(ObjectRef value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (value != null) {
                jgen.writeString(value.id());
            }
        }
    }

    public static class Deserialiser extends StdDeserializer<ObjectRef<?>> {

        public Deserialiser() { this(null); }
        public Deserialiser(Class<ObjectRef<?>> vc) { super(vc); }

        @Override
        public ObjectRef<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            var id = p.getText();
            var cache =  ctxt.findInjectableValue("stix_object_cache", null, null);
            return new ObjectRef<Bundleable>(id, null, (Parser.ObjectCache) cache);
        }
    }

    public static <T extends Bundleable> ObjectRef<T> createObjectRef(String id, Class<T> type) {
        return create(id, type);
    }

    public static <T extends Bundleable> ObjectRef<T> createObjectRef(T object) {
        return create(object);
    }

    public static <T extends Bundleable> ObjectRef<T> create(String id, Class<T> type) {
        return new ObjectRef<T>(id, null, null);
    }

    public static <T extends Bundleable> ObjectRef<T> create(T object) {
        return new ObjectRef<T>(object.getId(), object, null);
    }

    private final String id;
    private final Parser.ObjectCache cache;
    private final T object;

    public ObjectRef(String id, T object, Parser.ObjectCache cache) {
        assert id != null : "An object reference MUST contain an id";
        assert (cache != null && object == null) || (cache == null && object != null)
            : "An object reference must have one of: cache or object";
        this.id = id;
        this.cache = cache;
        this.object = object;
    }

    public String id() {
        return id;
    }

    @SuppressWarnings("unchecked")
    public Optional<T> object() {
        return Optional.ofNullable(object).or(() -> (Optional<? extends T>) cache.findObject(id));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ObjectRef.class.getSimpleName() + "[", "]")
            .add("id='" + id + "'")
            .add("object=" + object)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectRef<?> objectRef = (ObjectRef<?>) o;
        return Objects.equals(id, objectRef.id) && Objects.equals(object, objectRef.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, object);
    }
}
