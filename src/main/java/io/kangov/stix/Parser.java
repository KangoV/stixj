package io.kangov.stix;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.*;
import io.micronaut.validation.Validated;
import io.micronaut.validation.validator.Validator;
import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Validated
@Singleton
@SuppressWarnings("unused")
public class Parser {

    public static final String OBSERVED_DATA = "observed-data";
    public static final String OBJECT_REFS = "object_refs";
    public static final String REPORT = "report";
    public static final String RELATIONSHIP = "relationship";
    public static final String SOURCE_REF = "source_ref";
    public static final String TARGET_REF = "target_ref";
    public static final String SIGHTING = "sighting";
    public static final String WHERE_SIGHTED_REFS = "where_sighted_refs";
    public static final String OBSERVED_DATA_REFS = "observed_data_refs";
    public static final String SIGHTING_OF_REF = "sighting_of_ref";
    public static final String OBJECT_MARKING_REFS = "object_marking_refs";

    public static class ObjectCache implements Iterable<ObjectCache.Entry> {

        public record Entry(String id, ObjectNode objectNode, Bundleable object) {
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

        public Optional<Entry> findEntry(String id) {
            return Optional.ofNullable(entries.get(id));
        }

        public Optional<Bundleable> findObject(String id) {
            return findEntry(id).map(Entry::object);
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
            var cache = addToCache(node, new ObjectCache());
            var obj = processNode((ObjectNode) node, cache);
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

                // create and prime a cache
                var cache = new ObjectCache(); // addToCache(bundleNode.get(OBJECTS), new ObjectCache());
                var id = bundleNode.get(ID).asText();
                var type = bundleNode.get(TYPE).asText();

                log.debug("START: ObjectNode: {}", id);

                // now process the objects and created the bundle
                var builder = Bundle.builder().id(id).type(type);

                if (bundleNode.get(OBJECTS) instanceof ArrayNode objectsNode) {
                    for (JsonNode objectNode : objectsNode) {
                        builder.addObject(processNode((ObjectNode) objectNode, cache));
                    }
                }
                var bundle = builder.build();

                log.debug("END: ObjectNode: {}", id);
                return bundle;

            } else {
                throw new IllegalArgumentException("Not a valid Bundle");
            }

        } catch (Exception e) {
            throw new ParseException("Failed to deserialize a " + BUNDLE, e);
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

//        var id = objectNode.get(ID).asText();
//        var type = objectNode.get(TYPE).asText();

//        log.debug(">>> ObjectNode: {}", id);
        log.debug(">>> ObjectNode");

//        var cacheEntryOpt = cache.findEntry(id);
//
//        if (cacheEntryOpt.isPresent()) {
//            log.debug("Found cached entry for {}", id);
//            var bundleable = cacheEntryOpt.get().object();
//            if (bundleable != null) {
//                log.debug("Cached entry contains de-serialized object, so returning");
//                return bundleable;
//            } else {
//                log.debug("Cache entry not deserialised yet, continuing to process");
//            }
//        }

        /*
         * The following reference processing is needed as Jackson cannot seem to handle these. It can handle
         * forward and back references for parent and child relationships (many to one), but when it comes
         * to kind that stix has
         *
         * This has to be handled in code. This is the reason for the object cache.
         */

        processOptionalReference(CREATED_BY_REF, objectNode, cache);
        processReferences(OBJECT_MARKING_REFS, objectNode, cache);

        var id = objectNode.get(ID).asText();
        var type = objectNode.get(TYPE).asText();

        if (type.equals(REPORT)) {
            processReferences(OBJECT_REFS, objectNode, cache);
        }

        if (type.equals(OBSERVED_DATA)) {
            processReferences(OBJECT_REFS, objectNode, cache);
        }

        if (type.equals(RELATIONSHIP)) {
            processRequiredReference(SOURCE_REF, objectNode, cache);
            processRequiredReference(TARGET_REF, objectNode, cache);
        }

        if (type.equals(SIGHTING)) {
            processReferences(WHERE_SIGHTED_REFS, objectNode, cache);
            processReferences(OBSERVED_DATA_REFS, objectNode, cache);
            processRequiredReference(SIGHTING_OF_REF, objectNode, cache);
        }

        log.debug("<<< ObjectNode: {}", id);

        try {
            var object = objectMapper.treeToValue(objectNode, Bundleable.class);
            cache.put(ObjectCache.Entry.create(object));
            log.debug("=== Bundleable added to bundle and cached");
            return object;
        } catch (Exception e) {
            throw new ParseException("De-serialisation of "+id+" failed", e);
        }
    }

    /**
     * @see #processReference(String, ObjectNode, ObjectCache, boolean)
     */
    private <T extends Bundleable> void processRequiredReference(String fieldName, ObjectNode containerNode, ObjectCache cache) throws Exception {
        processReference(fieldName, containerNode, cache, true);
    }

    /**
     * @see #processReference(String, ObjectNode, ObjectCache, boolean)
     */
    private <T extends Bundleable> void processOptionalReference(String fieldName, ObjectNode containerNode, ObjectCache cache) throws Exception {
        processReference(fieldName, containerNode, cache, false);
    }

    /**
     * Process a node named {@code fieldName} which is a child of the {@code containerNode}. The cache is first checked
     * and an object is created and cached if necessary.
     *
     * @param fieldName The name of the child node
     * @param containerNode The containing node
     * @param cache to use to find a existing object
     * @param required true if the {@code fieldName} MUST exist
     * @param <T> The type of reference
     * @throws Exception is thrown if the {@code fieldName} node is not found and {@code required} is true
     */
    private <T extends Bundleable> void processReference(
            String fieldName,
            ObjectNode containerNode,
            ObjectCache cache,
            boolean required) throws Exception {
        var containerId = "/"; //containerNode.get("id").asText();
        //log.debug("Processing {} in {}", fieldName, containerId);
        var refNode = containerNode.get(fieldName);
        if (refNode != null) {
            //log.debug("Property {} found in {}", fieldName, containerId);
            var ref = new ObjectRef<T>(refNode.asText(), null, cache);  //refFactory.apply(refNode.asText(), obj);
            containerNode.remove(fieldName);
            containerNode.putPOJO(fieldName, ref);
            log.debug("--- replaced field {} in object {} with an ObjectRef instance", fieldName, containerId);
        } else {
            if (required) {
                throw new ParseException("Missing required attribute ["+fieldName+"] in "+containerId);
            }
            log.debug("--- property {} not found in {}, skipping", fieldName, containerId);
        }
    }

//    /**
//     * Returns a {@link Bundleable} instance retrieved from the provided {@code cache}. If the {@code JsonNode} within
//     * the found entry has not been processed, then it will be used to create the {@code Bundleable} object. The new
//     * object will then be added to the cache and returned.
//     *
//     * @param id The stix identifier of the entry to find in the cache
//     * @param cache The cache where pre and post JsonNodes are stored.
//     * @return a {@link Bundleable} instance retrieved from the provided {@code cache}
//     * @throws Exception if any exception occurs
//     */
//    private Bundleable resolveReference(String id, ObjectCache cache) throws Exception {
//        log.debug("Resolving reference: {}", id);
//        Bundleable target = null;
//        if (cache.contains(id)) {
//            var entry = cache.get(id);
//            target = entry.object();
//            if (target == null) {
//                log.debug("Cache entry {} found but not deserialised yet", id);
//                // cache entry not processed yet
//                target = processNode(entry.objectNode(), cache);
//                cache.put(ObjectCache.Entry.create(target));
//            } else {
//                log.debug("Cache entry {} found returning previously deserialised object", id);
//            }
//        }
//        return target;
//    }

    /**
     * Processes an array of object references within the provided {@code containerNode} and {@code fieldname}.
     * {@link ObjectRef} instances are used to store the object id and the actual object
     * itself. This provides for the situation where a reference ID points to an object that is not included.
     *
     * @param fieldName The fieldname of the array node
     * @param containerNode The node that contains the fieldname
     * @param cache The cache to be used to search for previously deserialized objects.
     * @param <T> The type of object being referenced
     * @throws Exception if an error occurs
     * @see ObjectRef
     */
    private <T extends Bundleable> void processReferences(
            String fieldName,
            JsonNode containerNode,
            ObjectCache cache) throws Exception {
        var node = containerNode.get(fieldName);
        if (node instanceof ArrayNode refs) {
            log.debug(">>> ArrayNode: {}", refs.asText());
            var list = new ArrayList<ObjectRef<T>>();
            for (int i = 0; i<refs.size(); i++) {
                var refNode = refs.get(i);
                var refId = refNode.asText();
                var ref = new ObjectRef<T>(refId, null, cache);
                list.add(ref);
            }
            refs.removeAll();
            list.forEach(ref -> {
                refs.addPOJO(ref);
                log.debug("--- added POJONode to Array: {}", ref);
            });
            log.debug("<<<: ArrayNode: {}", refs.asText());
        }
    }

    /**
     * Adds the provided {@code jsonNode} into the provided {@code cache}. If the jsonnode is an {@code ArrayNode},
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
