package io.kangov.stix.parser;

import com.fasterxml.jackson.databind.*;
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

    public @Valid Bundle read(String str) {
        var reader = new Reader(objectMapper(), objectCache());
        var bundle = reader.read(str);
        return bundle;
    }

    public @Valid <T extends Bundleable> T read(String str, Class<T> type) {
        var reader = new Reader(objectMapper(), objectCache());
        var bundle = reader.read(str);
        var objectOpt = bundle.stream(type).findFirst();
        return objectOpt.orElse(null);
    }

    public String write(@Valid Bundle bundle) {
        return new Writer(objectMapper()).write(bundle);
    }

    public String write(@Valid Bundleable bundleable) {
        return new Writer(objectMapper()).write(bundleable);
    }

    public void validate(@Valid Bundleable bundleable) {
        validator().validate(bundleable);
    }

    private ObjectMapper objectMapper() {
        return this.objectMapper;
    }

    private ObjectCache objectCache() {
        return this.objectCache;
    }

    private Validator validator() {
        return this.validator;
    }
}
