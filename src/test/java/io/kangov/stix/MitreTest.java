package io.kangov.stix;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class MitreTest {

    private static final Logger log = LoggerFactory.getLogger(MitreTest.class);

    @Inject
    Parser parser;
    @Inject Validator validator;

    @Test
    @DisplayName("enterprise-attack-1.0")
    void deser0() throws Exception {
        assertDeser("/mitre/enterprise-attack-1.0.json");
    }

    @Test
    @DisplayName("custom-object")
    void deser1() throws Exception {
        assertDeser("/mitre/custom-object.json");
    }

    private void assertDeser(String filename) throws Exception {
        var json1 = load(filename);
        var obj1  = parser.readBundle(json1);
        var json2 = parser.writeBundle(obj1);
        var obj2  = parser.readBundle(json2);
        assertThat(obj1).isEqualTo(obj2);
    }

    private String load(String filename) throws Exception {
        return Files.readString(Paths.get(this.getClass().getResource(filename).toURI()));
    }

}
