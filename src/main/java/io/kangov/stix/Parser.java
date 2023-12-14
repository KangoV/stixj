package io.kangov.stix;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.IdentityRef;
import io.kangov.stix.v21.common.type.SdoObjectRef;
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
import java.util.function.Function;

@Validated
@Singleton
@SuppressWarnings("unused")
public class Parser {

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

    public  <T> @Valid T readObject(String str, Class<T> bundleable) {
        try {
            log.debug("Deserializing {} to: {}", bundleable.getSimpleName(), str);
            var node = objectMapper.readTree(str);
            var cache = addToCache(node, new ObjectCache());
            var obj = processNode((ObjectNode) node, cache);
            log.debug("Deserialized {} to: {}", bundleable.getSimpleName(),  obj);
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

                log.debug("Deserialized {} to:\n{}", BUNDLE,  bundle);
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

    private static class ObjectCache implements Iterable<ObjectCache.Entry> {

        private record Entry(String id, ObjectNode objectNode, Bundleable bundleable) {
            private static Entry create(ObjectNode objectNode) {
                return new Entry(objectNode.get(ID).asText(), objectNode, null);
            }
            private static Entry create(Bundleable bundleable) {
                return new Entry(bundleable.getId(), null, bundleable);
            }
        }

        private final Map<String,Entry> entries = new HashMap<>();

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


    @Valid Bundleable processNode(ObjectNode objectNode, ObjectCache cache) throws Exception {

        var id = objectNode.get(ID).asText();
        var type = objectNode.get(TYPE).asText();

        log.debug("Processing {}", id);

        var cacheEntry = cache.get(id);

        // if we have a cache entry, and it has been processed, then return it
        if (cacheEntry != null) {
            log.debug("Found cached entry for {}", id);
            var bundleable = cache.get(id).bundleable();
            if (bundleable != null) {
                log.debug("Cached entry contains de-serialized object, so returning");
                return cache.get(id).bundleable();
            } else {
                log.debug("Cache entry not deserialised yet, continuing to process");
            }
        }

        // see if the node to process has a createdByRef node
        // if so, then we should process it and set the generated POJO on the object node. Jackson will then use
        // the pojo when de-serialising which is awesome!
        var createByRefNode = objectNode.get(CREATED_BY_REF);
        if (createByRefNode != null) {
            var refId = createByRefNode.asText();
            var identity = (Identity) processReference(refId, cache);
            IdentityRef identityRef = null;
            if (identity != null) {
                identityRef = IdentityRef.create(identity);
            } else {
                identityRef = IdentityRef.create(id);
            }
            objectNode.putPOJO(CREATED_BY_REF, identityRef);
        }

        // Now we can start looking for relationships
        if (type.equals("relationship")) {

            log.debug("processing standard relationship {}", id);

            var sdo = (SdoObject) createReference(objectNode, "source_ref", cache);
            objectNode.putPOJO("source_ref", new SdoObjectRef(sdo.getId(), sdo));

            sdo = (SdoObject) createReference(objectNode, "target_node", cache);
            objectNode.putPOJO("target_ref", new SdoObjectRef(sdo.getId(), sdo));

        }

        if (type.equals("sighting")) {

            log.debug("processing sighting {}", id);

            var sdo = (SdoObject) createReference(objectNode, "sighting_of_ref", cache);
            objectNode.putPOJO("sighting_of_ref", new SdoObjectRef(sdo.getId(), sdo));

            // Process the where sighted ref identifiers
            // - get the array node
            // - for each array entry
            //   - process the reference to return an object
            var refs = (ArrayNode) objectNode.get("where_sighted_refs");
            if (refs != null) {
                var list = new ArrayList<SdoObjectRef>();
                for (var iterator = refs.elements(); iterator.hasNext();) {
                    var refNode = iterator.next();
                    var obj = processReference(refNode.asText(), cache);
                    if (obj != null) {
                        list.add(new SdoObjectRef(obj.getId(), (SdoObject) obj));
                    } else {
                        list.add(new SdoObjectRef(refNode.asText(), null));
                    }
                    iterator.remove();
                }
                list.forEach(refs::addPOJO);
            }

            refs = (ArrayNode) objectNode.get("observed_data_refs");
            if (refs != null) {
                var list = new ArrayList<SdoObjectRef>();
                for (var iterator = refs.elements(); iterator.hasNext();) {
                    var refNode = iterator.next();
                    var obj = processReference(refNode.asText(), cache);
                    if (obj != null) {
                        list.add(new SdoObjectRef(obj.getId(), (SdoObject) obj));
                    } else {
                        list.add(new SdoObjectRef(refNode.asText(), null));
                    }
                    iterator.remove();
                }
                list.forEach(refs::addPOJO);
            }

        }



        try {
            var object = objectMapper.treeToValue(objectNode, Bundleable.class);
            cache.put(ObjectCache.Entry.create(object));
            return object;
        } catch (Exception e) {
            log.error("De-serialisation of {} failed", id);
            throw new ParseException(e);
        }
    }

    private Bundleable createReference(ObjectNode containingNode, String refNodeName, ObjectCache cache) throws Exception {
        Bundleable obj = null;
        var refNode = containingNode.get(refNodeName);
        if (refNode == null) {
            var type = containingNode.get(TYPE).asText();
            var id = containingNode.get(ID).asText();
            throw new ParseException("Missing attribute for ["+ type + ":(" + id + ")]: target_ref");
        }
        obj = processReference(refNode.asText(), cache);
        return obj;
    }

    private Bundleable processReference(String id, ObjectCache cache) throws Exception {
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
