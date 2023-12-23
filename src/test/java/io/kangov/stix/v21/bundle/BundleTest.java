package io.kangov.stix.v21.bundle;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.Stix;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import io.kangov.stix.v21.core.sdo.objects.Indicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;


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
            "confidence": 1,
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
        },
        {
            "type": "malware",
            "spec_version": "2.1",
            "id": "malware--3a41e552-999b-4ad3-bedc-332b6d9ff80c",
            "created": "2016-11-12T14:31:09.000Z",
            "modified": "2016-11-12T14:31:09.000Z",
            "is_family": true,
            "malware_types": [ "bot" ],
            "name": "IMDDOS"
        },
        {
            "type": "relationship",
            "spec_version": "2.1",
            "id": "relationship--81f12913-1372-4c96-85ec-E9034ac98aba",
            "created": "2016-11-23T10:42:39.000Z",
            "modified": "2016-11-23T10:42:39.000Z",
            "relationship_type": "consists-of",
            "source_ref": "malware--3a41e552-999b-4ad3-bedc-332b6d9ff80c",
            "target_ref": "indicator--8e2e2d2b-17d4-4cbf-938f-98ee46b3cd3f"
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
        .createdByRef(ObjectRef.createObjectRef(identity))
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
    void testWrite() {
        var str = parser.writeBundle(BUNDLE_OBJECT);
//        log.debug("To JSON : \n{}", str);
        assertThat(str).isNotNull();
    }

    @Test
    void testRead() throws Exception{
        var bundle = parser.readBundle(BUNDLE_STRING);
        assertThat(bundle).isNotNull();
        var actual = bundle.stream(Identity.class).findFirst().get();
        var indicator = bundle.stream(Indicator.class).findFirst().get();
        var expected = indicator.getCreatedByRef().object().get();
        assertThat(actual).isSameAs(expected);
    }

    @Test
    void testPerformance() {
        var executor = Executors.newWorkStealingPool(6);
        var count = 10_000;
        var start = Instant.now();
        for (int i=0; i< count; i++) {
            executor.execute(() -> parser.writeBundle(BUNDLE_OBJECT));
        }
        shutdownAndAwaitTermination(executor);
        var finish = Instant.now();
        var duration = Duration.between(start, finish);
        log.info("Completed {} iterations in {} ms (~{}/ms)", count, duration.toMillis(), count/duration.toMillis());
    }

//    private <T extends Identity> ObjectRef<Identity> getCreatedByRef(T t) {
//        var opt = t.getCreatedByRef();
//        //assertThat(opt).isPresent();
//        return opt; //.get();
//    }

    static void shutdownAndAwaitTermination(ExecutorService pool) {
        // Disable new tasks from being submitted
        pool.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                // Cancel currently executing tasks forcefully
                pool.shutdownNow();
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ex) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
