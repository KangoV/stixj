package io.kangov.stix.util;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.core.sdo.objects.*;
import io.kangov.stix.v21.core.sro.objects.Relationship;
import io.kangov.stix.v21.core.sro.objects.Sighting;
import io.kangov.stix.v0.OasisTest;
import io.kangov.stix.v21.meta.mdo.MarkingDefinition;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestBundle {

    public record Ref<T extends Bundleable>(Class<T> type, String id) {}

    public static final Ref<Sighting> SIGHTING = new Ref<>(Sighting.class, "sighting--6c9a1180-994e-4082-9a11-80994e308253");
    public static final Ref<Infrastructure> INFRASTRUCTURE = new Ref<>(Infrastructure.class, "infrastructure--d09c50cf-5bab-465e-9e2d-543912148b73");
    public static final Ref<Malware> MALWARE= new Ref<>(Malware.class,"malware--3a41e552-999b-4ad3-bedc-332b6d9ff80c");
    public static final Ref<Indicator> INDICATOR= new Ref<>(Indicator.class, "indicator--6dc15502-e387-4073-9098-23de8ac17099");
    public static final Ref<Identity> IDENTITY = new Ref<>(Identity.class, "identity--6c9a1180-994e-4082-9a11-80994e308253");
    public static final Ref<ObservedData> OBSERVED_DATA = new Ref<>(ObservedData.class,   "observed-data--6c9a1180-994e-4082-9a11-80994e308253");
    public static final Ref<Relationship> RELATIONSHIP_MALWARE_INFRASTRUCTURE = new Ref<>(Relationship.class, "relationship--37ac0c8d-f86d-4e56-aee9-914343959a4c");
    public static final Ref<MarkingDefinition> MARKING_DEFINITION_TLP_WHITE = new Ref<>(MarkingDefinition.class, "marking-definition--613f2e26-407d-48c7-9eca-b8e91df99dc9");
    public static final Ref<MarkingDefinition> MARKING_DEFINITION_STATEMENT = new Ref<>(MarkingDefinition.class, "marking-definition--4a0042fe-8b88-40fe-9600-dfa128ce6fbd");


    public static final String BUNDLE_JSON = load("/v21/stix/bundle/bundle.json");

    public static String load(String filename) {
        try {
            return Files.readString(Paths.get(OasisTest.class.getResource(filename).toURI()));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read "+filename, e);
        }
    }

}
