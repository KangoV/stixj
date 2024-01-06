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

import static io.kangov.stix.util.TestUtils.loadResource;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class IdentityTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(IdentityTest.class);
    private static final Class<Identity> TYPE = Identity.class;
    private static final int MOCK_COUNT = 200;
    private static String json_object;

    @Inject Mocks mock;
    @Inject Parser parser;
    @Inject Validator validator;

    @BeforeAll
    static void beforeAll() {
        json_object = loadResource(SDO_RESOURCES_ROOT + "identity.json");
        assertThat(json_object).isNotNull();
    }

    @Test
    void test_deser() {
        var expected = parser.read(json_object, TYPE);
        var json = parser.write(expected);
        var actual = parser.read(json).get(expected.getId(), TYPE);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void test_Name_not_blank() {
        var original = parser.read(json_object, TYPE);
        var modified = original.update(b -> b.name(null));
        var violations = validator.validate(modified);
        violations.forEach(v -> System.out.println("got: "+v.getMessage()));
        assertThat(violations).hasSize(1);
    }

    @Test
    void test_negative_confidence() {
        // container constraint, "Set<@Min(2) String>, so should generate a violation
        var original = parser.read(json_object, TYPE);
        var modified = original.update(b -> b.confidence(-2));
        var violations = validator.validate(modified);
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
        range(0, MOCK_COUNT).forEach(i -> {
            var expected = mock.mockIdentity();
            var string = parser.write(expected);
            var actual = parser.read(string, TYPE);
            assertThat(actual).as("(%s) -- expected json: %s", i, string).isEqualTo(expected);
        });
    }

}
