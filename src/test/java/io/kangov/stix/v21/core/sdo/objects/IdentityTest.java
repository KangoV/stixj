package io.kangov.stix.v21.core.sdo.objects;

import io.kangov.stix.Parser;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.common.mock.Mocks;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class IdentityTest {

    private static final Logger log = LoggerFactory.getLogger(IdentityTest.class);
    private static final int MOCK_COUNT = 200;

    @Inject Mocks mock;
    @Inject Parser parser;
    @Inject Validator validator;

    private String json = """
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
        """;

    private Identity.Builder builder;


    @BeforeEach
    void beforeEach() {
        this.builder = new Identity.Builder()
            .addLabels(
                "label_1",
                "label_2")
            .lang("en")
            .confidence(2)
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
                .putHash("SHA-256", "6db12788c37247f2316052e142f42f4b259d6561751e5f401a1ae2a6df9c674b")
                .externalId("external_id")
            )
            .customProperties(Map.of("x_key_1", "value_1"));
    }

    @Test
    void testRandom() {
        var object = mock.mockIdentity();
        range(0, MOCK_COUNT).forEach(i -> {
            var expected = mock.mockIdentity();
            var string = parser.writeObject(expected);
            var actual = parser.readObject(string, Identity.class);
            log.info("testing {}", i);
            assertThat(actual).as("(%s) -- expected json: %s", i, string).isEqualTo(expected);
        });
    }

    @Test
    void test_Name_not_blank() {
        var object = builder.name(null).build();
        var violations = validator.validate(object);
        assertThat(violations).hasSize(1);
    }

    @Test
    void test_Roles_below_min() {

        // container constraint, "Set<@Min(2) String>, so should generate a violation
        var object = builder
            .roles("single")
            .build();
        var violations = validator.validate(object);
        assertThat(violations).hasSize(1);

        // There are no roles, so nothing to test with @Min, so no violations should be generated.
        object = builder
            .roles(List.of())
            .build();
        violations = validator.validate(object);
        assertThat(violations).isEmpty();
    }

    @Test
    void test_String_ToObject() throws Exception{

        var string = """
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
            """;

        var object = parser.readObject(string, Identity.class);

        assertThat(object).isNotNull();

    }



}
