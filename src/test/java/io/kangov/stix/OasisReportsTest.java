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
public class OasisReportsTest {

    private static final Logger log = LoggerFactory.getLogger(OasisReportsTest.class);

    @Inject
    Parser parser;
    @Inject Validator validator;

    @Test
    @DisplayName("imddos_botnet")
    void deser0() throws Exception {
        assertDeser("/oasis/reports/damballa/imddos_botnet.json");
    }

    @Test
    @DisplayName("poison_ivy")
    void deser1() throws Exception {
        assertDeser("/oasis/reports/fireeye/poison_ivy.json");
    }

    @Test
    @DisplayName("apt1")
    void deser2() throws Exception {
        assertDeser("/oasis/reports/mandiant/apt1.json");
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
