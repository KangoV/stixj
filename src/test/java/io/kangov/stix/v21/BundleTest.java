package io.kangov.stix.v21;

import io.kangov.stix.Parser;
import io.kangov.stix.Stix;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import io.kangov.stix.v21.core.sdo.objects.Indicator;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.type;


public class BundleTest {

    private static final Logger log = LoggerFactory.getLogger(BundleTest.class);

    private static final String BUNDLE_STRING = """
    {
        "id": "bundle--6c9a1180-994e-4082-9a11-80994e308253",
        "type": "bundle",
        "objects": [
        {
            "id": "identity--6c9a1180-994e-4082-9a11-80994e308253",
            "type": "identity",
            "spec_version": "2.1",
            "created": "2023-11-15T17:24:14.356165Z",
            "modified": "2023-11-15T17:24:14.356165Z",
            "revoked": "true",
            "confidence": -2,
            "lang": "en",
            "name": "ACME Widget, Inc.",
            "description": "ACME Widgets is fictional ;)",
            "identity_class": "organization",
            "contact_information": "not_contactable",
            "x_prop_1": "value-1",
            "x_prop_2": "value-2"
        },
        {
            "type": "indicator",
            "spec_version": "2.1",
            "id": "indicator--8e2e2d2b-17d4-4cbf-938f-98ee46b3cd3f",
            "created_by_ref": "identity--6c9a1180-994e-4082-9a11-80994e308253",
            "created": "2016-04-06T20:03:48.000Z",
            "modified": "2016-04-06T20:03:48.000Z",
            "indicator_types": ["malicious-activity"],
            "name": "Poison Ivy Malware",
            "description": "This file is part of Poison Ivy",
            "pattern": "[ file:hashes.'SHA-256' = '4bac27393bdd9777ce02453256c5577cd02275510b2227f473d03f533924f877' ]",
            "pattern_type": "stix",
            "valid_from": "2016-01-01T00:00:00Z"
        }
    ]
}
    """;

    private static final Identity identity = Identity.create(i -> i
        .lang("en")
        .confidence(-1)
        .name("ACME Widget, Inc.")
        .description("ACME Widgets is fictional ;)")
        .identityClass("organization")
        .contactInformation("not_contactable")
        .customProperties(Map.of("x_key_1", "value_1"))
    );

    private static final Bundleable indicator = Indicator.create(i -> i
        .createdByRef(identity)
        .addIndicatorType("malicious-activity")
        .name("Poison Ivy Malware")
        .description("This file is part of Poison Ivy")
        .pattern("[ file:hashes.'SHA-256' = '4bac27393bdd9777ce02453256c5577cd02275510b2227f473d03f533924f877' ]")
        .patternType("stix")
        .validFrom(Instant.now())
    );

    private static final Bundle BUNDLE_OBJECT = Bundle.create(b -> b
        .addObjects(identity, indicator)
    );

    private Stix stix;
    private Parser parser;

    @BeforeEach
    void setup() {
        this.stix = Stix.get();
        this.parser = stix.parser();
        assertThat(stix).isNotNull();
    }

    @Test
    void testSerialise() {
        var str = parser.writeBundle(BUNDLE_OBJECT);
//        log.debug("To JSON : \n{}", str);
        assertThat(str).isNotNull();
    }

    @Test
    void testDeserialise() throws Exception{
        var bundle = parser.readBundle(BUNDLE_STRING);
        var indicator = bundle.getFirst(Indicator.class);
        var identity = getCreatedByRef(indicator);
        assertThat(identity.getIdentityClass()).contains("organization");
    }

    @Disabled
    @Test
    void testPerformance() {
        var count = 100_000;
        var start = Instant.now();
        for (int i=0; i< count; i++) {
            parser.writeBundle(BUNDLE_OBJECT);
        }
        var finish = Instant.now();
        log.info("Completed {} iterations in {} ms", count, Duration.between(start, finish).toMillis());
    }

    private <T extends SdoObject> Identity getCreatedByRef(T t) {
        var opt = t.getCreatedByRef();
        //assertThat(opt).isPresent();
        return opt; //.get();
    }
}
