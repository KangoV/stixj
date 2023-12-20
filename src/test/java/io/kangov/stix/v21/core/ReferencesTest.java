package io.kangov.stix.v21.core;

import io.kangov.stix.Parser;
import io.kangov.stix.v21.TestBundle;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.kangov.stix.v21.TestBundle.*;
import static org.assertj.core.api.Assertions.assertThat;


@MicronautTest
public class ReferencesTest {

    private static final Logger log = LoggerFactory.getLogger(ReferencesTest.class);

    @Inject
    Parser parser;

    private Bundle bundle;

    @BeforeEach
    void setup() {
        bundle = parser.readBundle(TestBundle.BUNDLE);
    }

    @Test
    void testRelationshipReferences() throws Exception{
        var malware = getObject(bundle, MALWARE);
        var relationship = getObject(bundle, RELATIONSHIP_MALWARE_INFRASTRUCTURE);
        var infrastructure = getObject(bundle, INFRASTRUCTURE);
        assertThat(relationship.getSourceRef().object()).isSameAs(malware);
        assertThat(relationship.getTargetRef().object()).isSameAs(infrastructure);
    }

    @Test
    void testSightingReferences() throws Exception{
        var sighting = getObject(bundle, SIGHTING);
        var createdByRef = getObject(bundle, IDENTITY);
        var sightingOfRef = getObject(bundle, INDICATOR);
        assertThat(sighting.getCreatedByRef().object()).isSameAs(createdByRef);
        assertThat(sighting.getSightingOfRef().object()).isSameAs(sightingOfRef);
    }

    private <T extends Bundleable> T getObject(Bundle bundle, TestBundle.Ref<T> ref) {
        var object = bundle.stream(ref.type()).filter(o -> o.getId().equals(ref.id())).findFirst().orElse(null);
        assertThat(object).isNotNull();
        return (T) object;
    }
}
