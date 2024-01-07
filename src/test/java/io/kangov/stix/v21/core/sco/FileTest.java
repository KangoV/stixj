package io.kangov.stix.v21.core.sco;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestBases;
import io.kangov.stix.util.mock.Mocks;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sco.extension.ScoExtensions;
import io.kangov.stix.v21.core.sco.extension.types.ArchiveFileExtension;
import io.kangov.stix.v21.core.sco.objects.File;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static io.kangov.stix.util.TestUtils.loadResource;
import static io.kangov.stix.v21.core.sco.extension.ScoExtensions.createScoExtensions;
import static io.kangov.stix.v21.core.sco.extension.types.ArchiveFileExtension.createArchiveFileExtension;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class FileTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(FileTest.class);
    private static final Class<File> TYPE = File.class;

    private static String object_json;
    private static String bundle_json;

    @Inject Mocks mock;
    @Inject Parser parser;
    @Inject Validator validator;

    private Bundle bundle = Bundle.create(b -> b
        .addObject(File.createFile(f -> f
            .name("foo.zip")
            .putHash("SHA-256", "35a01331e9ad96f751278b891b6ea09699806faedfa237d40513d92ad1b7100f")
            .mimeType("application/zip")
            .extensions(ScoExtensions.create(
                createArchiveFileExtension(a -> a
                    .addContainsRefs(
                        ObjectRef.create("file--019fde1c-94ca-5967-8b3c-a906a51d87ac"),
                        ObjectRef.create("file--94fc2163-dec3-5715-b824-6e689c4de865"),
                        ObjectRef.create("file--d07ff290-d7e0-545b-a2ff-04602a9e0b73")
                    )
                )
            ))
        ))
    );

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
    void testReadWriteObject() {
        var object = parser.read(object_json).get();
        var string = parser.write(object);
        assertThat(string).isNotNull();
    }

    @Test
    void testWriteObject() {
        var string = parser.write(bundle);
        assertThat(string).isNotNull();
        System.out.println(string);
    }

    @Test
    void testReadBundle() {
        var bundle = parser.read(bundle_json);
        assertThat(bundle).isNotNull();
    }

    @Test
    void testDeserBundle() {
        var bundle1 = parser.read(bundle_json).get();
        var json1 = parser.write(bundle1);
        var bundle2 = parser.read(json1).get();
        assertThat(bundle2).isEqualTo(bundle1);
    }
}
