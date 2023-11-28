package io.kangov.stix;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kangov.stix.bundle.Bundle;
import io.kangov.stix.bundle.Bundleable;
import io.micronaut.json.JsonMapper;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Validated
@Singleton
public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    private final ObjectMapper objectMapper;

    @Inject
    Parser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Valid
    public  <T> T readObject(String str, Class<T> bundleable) {
        try {
            log.debug("Serializing {} to: {}", bundleable.getSimpleName(), str);
            var obj = objectMapper.readValue(str, bundleable);
            log.debug("Serialized {} to: {}", bundleable.getSimpleName(),  obj);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize a " + bundleable.getSimpleName(), e);
        }
    }

    public String writeObject(@Valid Bundleable bundleable) {
        try {
            log.debug("Serializing {}", bundleable);
            var str = objectMapper.writeValueAsString(bundleable);
            log.debug("Serialized {} to: {}", bundleable.getClass().getSimpleName(), str);
            return str;
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize a " + bundleable.getClass().getSimpleName(), e);
        }
    }

    @Valid
    public Bundle readBundle(String str) {
        var name = Bundle.class.getSimpleName();
        try {
            log.debug("Serializing {} to: {}", name, str);
            var obj = objectMapper.readValue(str, Bundle.class);
            log.debug("Serialized {} to: {}", name,  obj);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize a " + name, e);
        }
    }

    public String writeBundle(@Valid Bundle bundle) {
        try {
            log.debug("Serializing {}", bundle);
            var str = objectMapper.writeValueAsString(bundle);
            log.debug("Serialized {} to: {}", bundle.getClass().getSimpleName(), str);
            return str;
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize a " + bundle.getClass().getSimpleName(), e);
        }
    }

    static Map<String, JsonNode> toMap(Iterable<Map.Entry<String, JsonNode>> nodes) {
        var map = new HashMap<String, JsonNode>();
        for (Map.Entry<String, JsonNode> entry : nodes) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public ObjectMapper objectMapper() {
        return this.objectMapper;
    }
}
