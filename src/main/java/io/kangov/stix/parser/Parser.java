package io.kangov.stix.parser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.kangov.stix.ParseException;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.micronaut.validation.Validated;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Validated
@Singleton
@SuppressWarnings("unused")
public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String OBJECTS = "objects";
    private static final String STIX_OBJECT_CACHE = "stix_object_cache";

    private final Validator validator;
    private final ObjectCache objectCache;
    private final ObjectMapper objectMapper;

    @Inject
    Parser(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
        try {
            var injectedValues = objectMapper.getInjectableValues();
            this.objectCache = (ObjectCache) injectedValues.findInjectableValue(STIX_OBJECT_CACHE, null, null, null);
        } catch (JsonMappingException e) {
            throw new ParseException("Could not find ObjectCache");
        }
    }

    public String writeBundle(@Valid Bundle bundle) {
        return write(bundle);
    }

    public String writeObject(@Valid Bundleable bundleable) {
        return write(bundleable);
    }

    public void validate(@Valid Bundleable bundleable) {
        validator.validate(bundleable);
    }

    public ObjectMapper objectMapper() {
        return this.objectMapper;
    }

    @SuppressWarnings("unchecked")
    public <T> @Valid T readObject(String str, Class<T> bundleable) {
        try {
            log.trace("Deserializing {} to: {}", bundleable.getSimpleName(), str);
            var node = objectMapper.readTree(str);
            var obj = processNode((ObjectNode) node, objectCache);
            log.trace("Deserialized {} to: {}", bundleable.getSimpleName(), obj);
            return (T) obj;
        } catch (Exception e) {
            throw new ParseException("Failed to deserialize a " + bundleable.getSimpleName(), e);
        }
    }

    public @Valid Bundle readBundle(String str) {
        try {
            var obj = objectMapper.readTree(str);
            if (obj instanceof ObjectNode bundleNode) {
                var builder = Bundle
                    .builder()
                    .id(bundleNode.get(ID).asText())
                    .type(bundleNode.get(TYPE).asText());
                if (bundleNode.get(OBJECTS) instanceof ArrayNode objectsNode) {
                    for (JsonNode objectNode : objectsNode) {
                        builder.addObject(processNode((ObjectNode) objectNode, objectCache));
                    }
                }
                return builder.build();
            } else {
                throw new IllegalArgumentException("Not a valid Bundle");
            }
        } catch (Exception e) {
            throw new ParseException("Failed to deserialize a bundle", e);
        }
    }

    private String write(Object object) {
        try {
            log.trace("Serializing {}", object);
            var str = objectMapper.writeValueAsString(object);
            log.trace("Serialized {} to: {}", object.getClass().getSimpleName(), str);
            return str;
        } catch (Exception e) {
            throw new ParseException("Failed to serialize a " + object.getClass().getSimpleName(), e);
        }
    }

    @Valid Bundleable processNode(ObjectNode objectNode, ObjectCache cache) throws Exception {
        try {
            log.debug("Deserialising: {}", objectNode.get(ID).asText());
            var object = objectMapper.treeToValue(objectNode, Bundleable.class);
            cache.put(ObjectCache.Entry.create(object));
            return object;
        } catch (Exception e) {
            throw new ParseException("De-serialisation failed", e);
        }
    }


}
