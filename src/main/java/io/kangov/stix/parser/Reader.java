package io.kangov.stix.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.kangov.stix.ParseException;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.micronaut.context.annotation.Prototype;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Prototype
public class Reader {

    private static final Logger log = LoggerFactory.getLogger(Reader.class);

    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String OBJECTS = "objects";

    private final ObjectCache objectCache;
    private final ObjectMapper objectMapper;

    public Reader(ObjectMapper objectMapper, ObjectCache objectCache) {
        this.objectMapper = objectMapper;
        this.objectCache = objectCache;
    }

    public @Valid Bundle read(String str) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(str);
        } catch (JsonProcessingException e) {
            throw new ParseException("Failed to read string");
        }
        var typeNode = rootNode.get(TYPE);
        if (typeNode == null) {
            throw new ParseException("Not a STIX object or bundle");
        }
        var type = typeNode.asText();
        if (type.equals("bundle")) {
            return processBundle(rootNode);
        }
        return processObject(rootNode);
    }

    @Valid Bundle processObject(JsonNode rootNode) {
        var obj = processNode((ObjectNode) rootNode, objectCache);
        return Bundle.create(b -> b.addObject(obj));
    }

    @Valid Bundle processBundle(JsonNode rootNode) {
        try {
            if (rootNode instanceof ObjectNode bundleNode) {
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
            if (e instanceof ParseException pe) {
                throw pe;
            }
            throw new ParseException("Failed to deserialize a bundle", e);
        }

    }

    @Valid Bundleable processNode(ObjectNode objectNode, ObjectCache cache) {
        try {
            log.trace("Deserialising: {}", objectNode.get(ID).asText());
            var object = objectMapper.treeToValue(objectNode, Bundleable.class);
            cache.put(ObjectCache.Entry.create(object));
            return object;
        } catch (Exception e) {
            throw new ParseException("De-serialisation failed", e);
        }
    }


}
