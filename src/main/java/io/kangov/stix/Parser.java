package io.kangov.stix;

import io.kangov.stix.bundle.Bundleable;
import io.micronaut.json.JsonMapper;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.serde.ObjectMapper;
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

    public  <T> @Valid T readObject(String str, Class<T> bundleable) {
        try {
            log.debug("Serializing {} to: {}", bundleable.getSimpleName(), str);
            var jsonNode = objectMapper.readValue(str, JsonNode.class);
            var typeNode = jsonNode.get("type");
            var type = typeNode != null ? typeNode.getStringValue() : "!UNKNOWN!";
            var obj = objectMapper.readValueFromTree(jsonNode,bundleable);
            log.debug("Serialized {} of type {} to: {}", bundleable.getSimpleName(), type, obj);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize a " + bundleable.getSimpleName(), e);
        }
    }

    public String writeObject(@Valid Bundleable bundleable) {
        try {
            log.debug("Serializing {}", bundleable);
            var jsonNode = objectMapper.writeValueToTree(bundleable);
            var str = objectMapper.writeValueAsString(jsonNode);
            log.debug("Serialized {} to: {}", bundleable.getClass().getSimpleName(), str);
            return str;
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize a " + bundleable.getClass().getSimpleName(), e);
        }
    }

    static Map<String, JsonNode> toMap(Iterable<Map.Entry<String, JsonNode>> nodes) {
        var map = new HashMap<String, JsonNode>();
        for (Map.Entry<String, JsonNode> entry : nodes) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

}
