package io.kangov.stix.common;

import io.kangov.stix.Parser;
import io.kangov.stix.Stix;
import io.kangov.stix.bundle.Bundle;
import io.kangov.stix.core.sdo.objects.Identity;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

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
          "created_by_ref": "identity--f431f809-377b-45e0-aa1c-6a4751cae5ff",
          "created": "2023-11-15T17:24:14.356165Z",
          "modified": "2023-11-15T17:24:14.356165Z",
          "revoked": "true",
          "labels": [
            "label_1",
            "label_2"
          ],
          "confidence": -2,
          "external_references": [{
            "source_name": "veris",
            "description": "description",
            "external_id": "0001AA7F-C601-424A-B2B8-BE6C9F5164E7",
            "url": "https://github.com/vz-risk/VCDB/blob/125307638178efddd3ecfe2c267ea434667a4eea/data/json/validated/0001AA7F-C601-424A-B2B8-BE6C9F5164E7.json",
            "hashes": {
              "SHA-256": "6db12788c37247f2316052e142f42f4b259d6561751e5f401a1ae2a6df9c674b"
            }
          }],
          "lang": "en",
          "name": "ACME Widget, Inc.",
          "description": "ACME Widgets is fictional ;)",
          "roles": [
            "role_1",
            "role_2"
          ],
          "identity_class": "organization",
          "sectors": [
            "communications",
            "defense",
            "infrastructure"
          ],
          "contact_information": "not_contactable",
          "x_prop_1": "value-1",
          "x_prop_2": "value-2"
        }
      ]
    }
    """;

    private static final Bundle BUNDLE_OBJECT = Bundle.create(b -> b
        .addObject(Identity.create(i -> i
            .addLabels(
                "label_1",
                "label_2")
            .lang("en")
            .confidence(-1)
            .name("ACME Widget, Inc.")
            .description("ACME Widgets is fictional ;)")
            .addRoles(
                "role_1",
                "role_2")
            .identityClass("organization")
            .addSectors(
                "communications",
                "defense",
                "infrastructure")
            .contactInformation("not_contactable")
            .addExternalReference(f -> f
                .sourceName("veris")
                .description("description")
                .url("https://github.com/vz-risk/VCDB/blob/125307638178efddd3ecfe2c267ea434667a4eea/data/json/validated/0001AA7F-C601-424A-B2B8-BE6C9F5164E7.json")
                .putHashe("SHA-256", "6db12788c37247f2316052e142f42f4b259d6561751e5f401a1ae2a6df9c674b")
                .externalId("external_id")
            )
            .customProperties(Map.of("x_key_1", "value_1"))
        ))
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
        assertThat(str).isNotNull();
    }

    @Test
    void testDeserialise() throws Exception{
        var object = parser.readBundle(BUNDLE_STRING);
        assertThat(object).isNotNull();
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

}
