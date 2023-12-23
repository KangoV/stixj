package io.kangov.stix.v21.core;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.v21.TestBundle;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.meta.mdo.GranularMarking;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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
        assertThat(relationship.getSourceRef().object().get()).isSameAs(malware);
        assertThat(relationship.getTargetRef().object().get()).isSameAs(infrastructure);
    }

    @Test
    void testSightingReferences() throws Exception{
        var sighting = getObject(bundle, SIGHTING);
        var createdByRef = getObject(bundle, IDENTITY);
        var sightingOfRef = getObject(bundle, INDICATOR);
        assertThat(sighting.getCreatedByRef().object().get()).isSameAs(createdByRef);
        assertThat(sighting.getSightingOfRef().object().get()).isSameAs(sightingOfRef);
    }

    @Test
    void testObjectMarkingRefs() {
        var marking1 = getObject(bundle, MARKING_DEFINITION_TLP_WHITE);
        var marking2 = getObject(bundle, MARKING_DEFINITION_STATEMENT);
        var indicator = getObject(bundle, INDICATOR);
        var markings = indicator.getObjectMarkingRefs().stream()
            .map(ObjectRef::object)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        assertThat(markings).containsExactlyInAnyOrder(marking1,marking2);
    }

    @Test
    void testGranularMarkings() {
        var marking1 = getObject(bundle, MARKING_DEFINITION_TLP_WHITE);
        var indicator = getObject(bundle, INDICATOR);
        var markings = indicator.getGranularMarkings().stream()
            .map(GranularMarking::getMarkingRef)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(ObjectRef::object)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        assertThat(markings).containsExactlyInAnyOrder(marking1);
    }


    private <T extends Bundleable> T getObject(Bundle bundle, TestBundle.Ref<T> ref) {
        var object = bundle.stream(ref.type()).filter(o -> o.getId().equals(ref.id())).findFirst().orElse(null);
        assertThat(object).isNotNull();
        return (T) object;
    }
}
