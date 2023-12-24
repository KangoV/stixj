package io.kangov.stix.v21.core.sdo.objects;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestBases;
import io.kangov.stix.util.TestUtils;
import io.kangov.stix.util.mock.Mocks;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class IdentityTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(IdentityTest.class);
    private static final int MOCK_COUNT = 200;
    private static String json;

    @Inject Mocks mock;
    @Inject Parser parser;
    @Inject Validator validator;

    @BeforeAll
    static void beforeAll() {
        json = TestUtils.loadResource("/v21/identity.json");
        assertThat(json).isNotNull();
    }

    @Test
    void testRead() {
        var json0 = TestUtils.loadResource("/v21/identity.json");
        var object = parser.read(json0, Identity.class);
        assertThat(object).isNotNull();
    }

    @Test
    void testWrite() throws Exception {
        var object = parser.read(json);
        var string = parser.write(object);
        assertThat(string).isNotNull();
    }

    @Test
    void test_Name_not_blank() {
        var object = Identity.builder()
            .from(parser.read(json, Identity.class))
            .name(null)
            .build();
        var violations = validator.validate(object);
        violations.stream().forEach(v -> System.out.println("got: "+v.getMessage()));
        assertThat(violations).hasSize(1);
    }

    @Test
    void test_negative_confidence() {

        // container constraint, "Set<@Min(2) String>, so should generate a violation
        var object = Identity.builder()
            .from(parser.read(json, Identity.class))
            .confidence(-2)
            .name(null)
            .build();
        var violations = validator.validate(object);
        assertThat(violations).isNotNull();
        violations.stream().forEach(v -> {
            var root = v.getRootBean();
            var leaf = v.getLeafBean();
            log.debug("{} -> {}", v.getPropertyPath(), v.getMessage());
        });
        assertThat(violations).hasSize(1);

    }

    @Test
    void testRandom() {
        var object = mock.mockIdentity();
        range(0, MOCK_COUNT).forEach(i -> {
            var expected = mock.mockIdentity();
            var string = parser.write(expected);
            var actual = parser.read(string, Identity.class);
            assertThat(actual).as("(%s) -- expected json: %s", i, string).isEqualTo(expected);
        });
    }

}
