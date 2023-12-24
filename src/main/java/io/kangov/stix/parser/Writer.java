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
public class Writer {

    private static final Logger log = LoggerFactory.getLogger(Writer.class);
    private final ObjectMapper objectMapper;

    Writer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    String write(Object object) {
        try {
            log.trace("Serializing {}", object);
            var str = objectMapper.writeValueAsString(object);
            log.trace("Serialized {} to: {}", object.getClass().getSimpleName(), str);
            return str;
        } catch (Exception e) {
            throw new ParseException("Failed to serialize a " + object.getClass().getSimpleName(), e);
        }
    }

}
