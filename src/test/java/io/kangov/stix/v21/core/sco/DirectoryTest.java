package io.kangov.stix.v21.core.sco;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestBases;
import io.kangov.stix.util.TestUtils;
import io.kangov.stix.util.mock.Mocks;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.core.sco.objects.Directory;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.kangov.stix.util.TestUtils.loadResource;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class DirectoryTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(DirectoryTest.class);
    private static final Class<? extends Bundleable> TYPE = Directory.class;

    private static String json;

    @Inject Mocks mock;
    @Inject Parser parser;
    @Inject Validator validator;

    @BeforeAll
    static void beforeAll() {
        json = loadResource(SCO_RESOURCES_ROOT + "directory.json");
        assertThat(json).isNotNull();
    }

    @Test
    void testRead() {
        var object = parser.read(json, TYPE); // stix sco file
        assertThat(object).isNotNull();
    }

    @Test
    void testWrite() {
        var object = parser.read(json);
        var string = parser.write(object);
        assertThat(string).isNotNull();
    }

}
