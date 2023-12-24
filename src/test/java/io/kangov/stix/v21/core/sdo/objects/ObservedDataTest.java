package io.kangov.stix.v21.core.sdo.objects;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestUtils;
import io.kangov.stix.util.mock.Mocks;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.core.sco.objects.XNominetBlock;
import io.kangov.stix.v21.core.sco.objects.XNominetThreatFeedSource;
import io.kangov.stix.v21.core.sro.objects.Sighting;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class ObservedDataTest {

    private static final Logger log = LoggerFactory.getLogger(ObservedDataTest.class);
    private static String json;

    @Inject Parser parser;

    @BeforeAll
    static void beforeAll() {
        json = TestUtils.loadResource("/v21/observed-data.json");
        assertThat(json).isNotNull();
    }

    @Test
    void testRead() throws Exception {
        var object = parser.read(json, ObservedData.class);
        assertThat(object).isNotNull();
    }

    @Test
    void testWrite() throws Exception {
        var object = parser.read(json);
        var string = parser.write(object);
        assertThat(string).isNotNull();
    }

}
