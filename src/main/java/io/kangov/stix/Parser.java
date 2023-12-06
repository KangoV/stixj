package io.kangov.stix;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    private final ObjectMapper objectMapper;
    private final Validator validator;

    @Inject
    Parser(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    public  <T> @Valid T readObject(String str, Class<T> bundleable) {
        try {
            log.debug("Deserializing {} to: {}", bundleable.getSimpleName(), str);
            var obj = objectMapper.readValue(str, bundleable);
            log.debug("Deserialized {} to: {}", bundleable.getSimpleName(),  obj);
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

    public @Valid Bundle readBundle(String str) {
        var name = Bundle.class.getSimpleName();
        try {
            log.debug("Deserializing {} to: {}", name, str);
            var obj = objectMapper.readValue(str, Bundle.class);
            log.debug("Deserialized {} to: {}", name,  obj);
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

    public void validate(@Valid Bundleable bundleable) {
        validator.validate(bundleable);
    }

    public ObjectMapper objectMapper() {
        return this.objectMapper;
    }
}
