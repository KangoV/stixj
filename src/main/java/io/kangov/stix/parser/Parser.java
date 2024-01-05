package io.kangov.stix.parser;

import com.fasterxml.jackson.databind.*;
import io.kangov.stix.ParseException;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.validation.Validated;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public @Valid Bundle read(String str) {
        var reader = new Reader(this);
        var bundle = reader.read(str);
        return bundle;
    }

    public @Valid <T extends Bundleable> T read(String str, Class<T> type) {
        var reader = new Reader(this);
        var bundle = reader.read(str);
        var objectOpt = bundle.stream(type).findFirst();
        return objectOpt.orElse(null);
    }

    public String write(@Valid Bundle bundle) {
        return new Writer(this).write(bundle);
    }

    public String write(@Valid Bundleable bundleable) {
        return new Writer(this).write(bundleable);
    }

    public void validate(@Valid Bundleable bundleable) {
        validator().validate(bundleable);
    }

    public <T extends Bundleable> ObjectRef<T> createRef(String id) {
        return new ObjectRef<>(id, null, objectCache());
    }

    public <T extends Bundleable> ObjectRef<T> createRef(T t) {
        return new ObjectRef<>(t.getId(), t, objectCache());
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
