package io.kangov.stix;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.*;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import io.micronaut.validation.Validated;
import io.micronaut.validation.validator.Validator;
import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.*;

@Validated
@Singleton
@SuppressWarnings("unused")
public class Parser {

    private static class ObjectCache implements Iterable<ObjectCache.Entry> {

        private record Entry(String id, ObjectNode objectNode, Bundleable bundleable) {
            private static Entry create(ObjectNode objectNode) {
                return new Entry(objectNode.get(ID).asText(), objectNode, null);
            }

            private static Entry create(Bundleable bundleable) {
                return new Entry(bundleable.getId(), null, bundleable);
            }
        }

        private final Map<String, Entry> entries = new HashMap<>();

        @Nonnull
        @Override
        public Iterator<Entry> iterator() {
            return entries.values().iterator();
        }

        public void put(Entry entry) {
            entries.put(entry.id(), entry);
        }

        public boolean contains(String id) {
            return entries.containsKey(id);
        }

        public Entry get(String id) {
            return entries.get(id);
        }
    }

    private static final Logger log = LoggerFactory.getLogger(Parser.class);
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String OBJECTS = "objects";
    public static final String CREATED_BY_REF = "created_by_ref";
    public static final String BUNDLE = Bundle.class.getSimpleName();

    private final ObjectMapper objectMapper;
    private final Validator validator;

    @Inject
    Parser(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    public <T> @Valid T readObject(String str, Class<T> bundleable) {
        try {
            log.debug("Deserializing {} to: {}", bundleable.getSimpleName(), str);
            var node = objectMapper.readTree(str);
            var cache = addToCache(node, new ObjectCache());
            var obj = processNode((ObjectNode) node, cache);
            log.debug("Deserialized {} to: {}", bundleable.getSimpleName(), obj);
            return (T) obj;
        } catch (Exception e) {
            throw new ParseException("Failed to deserialize a " + bundleable.getSimpleName(), e);
        }
    }

    public String writeObject(@Valid Bundleable bundleable) {
        try {
            log.debug("Serializing {}", bundleable);
            var str = objectMapper.writeValueAsString(bundleable);
            log.debug("Serialized {} to: {}", bundleable.getClass().getSimpleName(), str);
            return str;
        } catch (Exception e) {
            throw new ParseException("Failed to serialize a " + bundleable.getClass().getSimpleName(), e);
        }
    }

    public @Valid Bundle readBundle(String str) {
        try {
            log.debug("Deserializing {} from:\n{}", BUNDLE, str);

            var obj = objectMapper.readTree(str);

            log.debug("Read {} into JsonNode:\n{}", BUNDLE, obj);

            if (obj instanceof ObjectNode bundleNode) {

                // create and prime a cache
                var cache = addToCache(bundleNode.get(OBJECTS), new ObjectCache());

                // now process the objects and created the bundle
                var builder = Bundle.builder()
                    .id(bundleNode.get(ID).asText())
                    .type(bundleNode.get(TYPE).asText());

                if (bundleNode.get(OBJECTS) instanceof ArrayNode objectsNode) {
                    for (JsonNode objectNode : objectsNode) {
                        builder.addObject(processNode((ObjectNode) objectNode, cache));
                    }
                }
                var bundle = builder.build();

                log.debug("Deserialized {} to:\n{}", BUNDLE, bundle);
                return bundle;

            } else {
                throw new IllegalArgumentException("Not a valid Bundle");
            }

        } catch (Exception e) {
            throw new ParseException("Failed to deserialize a " + BUNDLE, e);
        }
    }

    public String writeBundle(@Valid Bundle bundle) {
        try {
            log.debug("Serializing {}", bundle);
            var str = objectMapper.writeValueAsString(bundle);
            log.debug("Serialized {} to: {}", bundle.getClass().getSimpleName(), str);
            return str;
        } catch (Exception e) {
            throw new ParseException("Failed to serialize a " + bundle.getClass().getSimpleName(), e);
        }
    }

    public void validate(@Valid Bundleable bundleable) {
        validator.validate(bundleable);
    }

    public ObjectMapper objectMapper() {
        return this.objectMapper;
    }



    @Valid Bundleable processNode(ObjectNode objectNode, ObjectCache cache) throws Exception {

        var id = objectNode.get(ID).asText();
        var type = objectNode.get(TYPE).asText();

        log.debug("Processing {}", id);

        var cacheEntry = cache.get(id);

        if (cacheEntry != null) {
            log.debug("Found cached entry for {}", id);
            var bundleable = cache.get(id).bundleable();
            if (bundleable != null) {
                log.debug("Cached entry contains de-serialized object, so returning");
                return bundleable;
            } else {
                log.debug("Cache entry not deserialised yet, continuing to process");
            }
        }

        processOptionalReference(CREATED_BY_REF, objectNode, cache, (i, b) -> new IdentityRef(i, (Identity) b));

        if (type.equals("observed-data")) {
            processReferences("object_refs", objectNode, cache, BundleableRef::new);
        }
        if (type.equals("report")) {
            processReferences("object_refs", objectNode, cache, BundleableRef::new);
        }
        if (type.equals("relationship")) {
            processRequiredReference("source_ref", objectNode, cache, BundleableRef::new);
            processRequiredReference("target_ref", objectNode, cache, BundleableRef::new);
        }
        if (type.equals("sighting")) {
            processReferences("where_sighted_refs", objectNode, cache, (i, b) -> new SdoObjectRef(i, (SdoObject) b));
            processReferences("observed_data_refs", objectNode, cache, (i, b) -> new SdoObjectRef(i, (SdoObject) b));
            processRequiredReference("sighting_of_ref", objectNode, cache, (i, b) -> new SdoObjectRef(i, (SdoObject) b));
        }

        try {
            var object = objectMapper.treeToValue(objectNode, Bundleable.class);
            cache.put(ObjectCache.Entry.create(object));
            return object;
        } catch (Exception e) {
            throw new ParseException("De-serialisation of "+id+" failed", e);
        }
    }

    private <T extends Bundleable> void processRequiredReference(String fieldName, ObjectNode containerNode, ObjectCache cache, BiFunction<String,Bundleable,ObjectRef<T>> refFactory) throws Exception {
        processReference(fieldName, containerNode, cache, true, refFactory);
    }

    private <T extends Bundleable> void processOptionalReference(String fieldName, ObjectNode containerNode, ObjectCache cache, BiFunction<String,Bundleable,ObjectRef<T>> refFactory) throws Exception {
        processReference(fieldName, containerNode, cache, false, refFactory);
    }

    /**
     * Process a node named {@code fieldname} which is a child of the {@code containerNode}. The cache is first checked
     * and an object is created and cached if necessary.
     *
     * @param fieldName The name of the child node
     * @param containerNode The containing node
     * @param cache to use to find a existing object
     * @param required true if the {@code fieldName} MUST exist
     * @param refFactory The factory to create the correct type of reference object
     * @param <T> The type of reference
     * @throws Exception is thrown if the {@code fieldName} node is not found and {@code required} is true
     */
    private <T extends Bundleable> void processReference(
            String fieldName,
            ObjectNode containerNode,
            ObjectCache cache,
            boolean required,
            BiFunction<String,Bundleable,ObjectRef<T>> refFactory) throws Exception {
        var refNode = containerNode.get(fieldName);
        if (refNode != null) {
            var obj = resolveReference(refNode.asText(), cache); // can return null
            var ref = refFactory.apply(refNode.asText(), obj);
            containerNode.putPOJO(fieldName, ref);
        } else {
            if (required) {
                var type = containerNode.get(TYPE).asText();
                var id = containerNode.get(ID).asText();
                throw new ParseException("Missing required attribute for [" + type + ":(" + id + ")]: " + fieldName);
            }
        }
    }

    /**
     * Returns a {@link Bundleable} instance retrieved from the provided {@code cache}. If the {@code JsonNode} within
     * the found entry has not been processed, then it will be used to create the {@code Bundleable} object. The new
     * object will then be added to the cache and returned.
     *
     * @param id The stix identifier of the entry to find in the cache
     * @param cache The cache where pre and post JsonNodes are stored.
     * @return a {@link Bundleable} instance retrieved from the provided {@code cache}
     * @throws Exception if any exception occurs
     */
    private Bundleable resolveReference(String id, ObjectCache cache) throws Exception {
        log.debug("processing forward reference: {}", id);
        Bundleable target = null;
        if (cache.contains(id)) {
            var entry = cache.get(id);
            target = entry.bundleable();
            if (target == null) {
                // cache entry not processed yet
                target = processNode(entry.objectNode(), cache);
                cache.put(ObjectCache.Entry.create(target));
            }
        }
        return target;
    }

    /**
     * Processes an array of object references within the provided {@code containerNode} and {@code fieldname}.
     * {@link ObjectRef} instances such as {@link SdoObjectRef} are used to store the object id and the actual object
     * itself. This provides for the situation where a reference ID points to an object that is not included.
     *
     * @param fieldName The fieldname of the array node
     * @param containerNode The node that containes the fieldname
     * @param cache The cache to be used to search for previously deserialized objects.
     * @param refFactory The bi-function used to generate the correct reference object
     * @param <T> The type of object being referenced
     * @throws Exception if an error occurs
     * @see ObjectRef
     */
    private <T extends Bundleable> void processReferences(
            String fieldName,
            JsonNode containerNode,
            ObjectCache cache,
            BiFunction<String,Bundleable,ObjectRef<T>> refFactory) throws Exception {
        var refs = (ArrayNode) containerNode.get(fieldName);
        if (refs != null) {
            var list = new ArrayList<ObjectRef<T>>();
            for (var iterator = refs.elements(); iterator.hasNext();) {
                var refNode = iterator.next();
                var refId = refNode.asText();
                var obj = resolveReference(refId, cache);
                var ref = refFactory.apply(refId, obj);
                list.add(ref);
                iterator.remove();
            }
            list.forEach(refs::addPOJO);
        }
    }

    /**
     * Adds the provided {@code jsonNode} into the provided {@code cache}. If the jsonnode is an {@code Arraynode},
     * then it's elements are added instead.
     *
     * @param jsonNode The json node to add or the container of the nodes to add
     * @param cache the cache to add to
     * @return the cache for chaining
     */
    private static ObjectCache addToCache(JsonNode jsonNode, ObjectCache cache) {
        if (jsonNode instanceof ArrayNode objectsNode) {
            for (JsonNode objectNode : objectsNode) {
                cache.put(ObjectCache.Entry.create((ObjectNode) objectNode));
            }
        } else {
            cache.put(ObjectCache.Entry.create((ObjectNode) jsonNode));
        }
        return cache;
    }

}
