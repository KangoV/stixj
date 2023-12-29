package io.kangov.stix.v21.core.sdo.objects;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestBases;
import io.kangov.stix.util.TestUtils;
import io.kangov.stix.v21.bundle.Bundleable;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.kangov.stix.util.TestUtils.loadResource;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class ObservedDataTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(ObservedDataTest.class);
    private static final Class<? extends Bundleable> TYPE = ObservedData.class;
    private static String json;

    @Inject Parser parser;

    @BeforeAll
    static void beforeAll() {
        json = loadResource(SDO_RESOURCES_ROOT + "observed-data.json");
        assertThat(json).isNotNull();
    }

    @Test
    void testRead() throws Exception {
        var object = parser.read(json, TYPE);
        assertThat(object).isNotNull();
    }

    @Test
    void testWrite() throws Exception {
        var object = parser.read(json);
        var string = parser.write(object);
        assertThat(string).isNotNull();
    }

}
