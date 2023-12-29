package io.kangov.stix.v21.core.sco;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestBases;
import io.kangov.stix.util.mock.Mocks;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.core.sco.objects.File;
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
public class FileTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(FileTest.class);
    private static final Class<? extends Bundleable> TYPE = File.class;

    private static String object_json;
    private static String bundle_json;

    @Inject Mocks mock;
    @Inject Parser parser;
    @Inject Validator validator;

    @BeforeAll
    static void beforeAll() {
        object_json = loadResource(SCO_RESOURCES_ROOT + "file.json");
        assertThat(object_json).isNotNull();
        bundle_json = loadResource(SCO_RESOURCES_ROOT + "file_bundle.json");
        assertThat(bundle_json).isNotNull();
    }

    @Test
    void testReadObject() {
        var object = parser.read(object_json, TYPE); // stix sco file
        assertThat(object).isNotNull();
    }

    @Test
    void testWriteObject() {
        var object = parser.read(object_json);
        var string = parser.write(object);
        assertThat(string).isNotNull();
    }

    @Test
    void testReadBundle() {
        var bundle = parser.read(bundle_json);
        assertThat(bundle).isNotNull();
    }
}
