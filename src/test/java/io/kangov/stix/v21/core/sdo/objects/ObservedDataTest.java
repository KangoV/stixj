package io.kangov.stix.v21.core.sdo.objects;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestBases;
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
    private static final Class<ObservedData> TYPE = ObservedData.class;
    private static String json_object;

    @Inject Parser parser;

    @BeforeAll
    static void beforeAll() {
        json_object = loadResource(SDO_RESOURCES_ROOT + "observed-data.json");
        assertThat(json_object).isNotNull();
    }

    @Test
    void testRead() throws Exception {
        var object = parser.read(json_object, TYPE);
        assertThat(object).isNotNull();
    }

    @Test
    void testWrite() throws Exception {
        var result = parser.read(json_object, TYPE);
        var object = result.get();
        var string = parser.write(object);
        assertThat(string).isNotNull();
    }

}
