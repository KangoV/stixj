package io.kangov.stix.parser;

import com.fasterxml.jackson.databind.*;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.validation.Validated;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

@Validated
@Prototype
@SuppressWarnings("unused")
public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    private static final String STIX_OBJECT_CACHE = "stix_object_cache";

    private final Validator validator;
    private final Cache objectCache;
    private final ObjectMapper objectMapper;

    @Inject
    Parser(ObjectMapper objectMapper, Validator validator) {
        assert objectMapper != null : "Missing object mapper";
        assert validator != null : "Missing validator";
        this.objectMapper = objectMapper;
        this.validator = validator;
        try {
            var injectedValues = objectMapper.getInjectableValues();
            var objectCache = (Cache) injectedValues.findInjectableValue(STIX_OBJECT_CACHE, null, null, null);
            assert objectCache != null : "Object cache not found within the object mapper";
            this.objectCache = objectCache;
        } catch (JsonMappingException e) {
            throw new IllegalStateException("Could not find ObjectCache", e);
        }
    }

    public Result<Bundle> read(String str) {
        var reader = new Reader(this);
        var start = Instant.now();
        var bundle = reader.read(str);
        var duration = Duration.between(start, Instant.now());
        var violations = validator.validate(bundle, Bundle.class);
        var result = ResultImpl.<Bundle> builder()
            .addAllConstrainViolations(violations)
            .get(bundle)
            .duration(duration)
            .type(Result.Type.BUNDLE)
            .build();
        return result;
    }

    public <T extends Bundleable> Result<T> read(String str, Class<T> type) {
        var reader = new Reader(this);
        var start = Instant.now();
        var bundle = reader.read(str);
        var duration = Duration.between(start, Instant.now());
        var builder = ResultImpl.<T> builder();
        var objectOpt = bundle.find(type).findFirst();
        if (objectOpt.isPresent()) {
            var object = objectOpt.get();
            var violations = validator.validate(object, type);
            builder.addAllConstrainViolations(violations);
            builder.get(object);
        }
        builder.duration(duration);
        builder.type(Result.Type.OBJECT);
        var result = builder.build();
        return result;
    }

    public String write(@Valid Bundle bundle) {
        return new Writer(this).write(bundle);
    }

    public String write(@Valid Bundleable bundleable) {
        return new Writer(this).write(bundleable);
    }

    ObjectMapper objectMapper() {
        return this.objectMapper;
    }

    Cache objectCache() {
        return this.objectCache;
    }

    Validator validator() {
        return this.validator;
    }

}
