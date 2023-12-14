package io.kangov.stix;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class OasisExamplesTest {

    private static final Logger log = LoggerFactory.getLogger(OasisExamplesTest.class);

    @Inject Parser parser;
    @Inject Validator validator;

    @Test
    void deser0() throws Exception {
        assertDeser("/oasis/examples/defining_campaigns_vs_threat_actors_vs_intrusion_sets.json");
    }

    @Test
    void deser1() throws Exception {
        assertDeser("/oasis/examples/identifiying_a_threat_actor_profile.json");
    }

    @Test
    void deser2() throws Exception {
        assertDeser("/oasis/examples/indicator_for_malicious_url.json");
    }

    @Test
    void deser3() throws Exception {
        assertDeser("/oasis/examples/malware_indicator_for_file_hash.json");
    }

    @Test
    void deser4() throws Exception {
        assertDeser("/oasis/examples/sighting_of_an_indicator.json");
    }

    @Test
    void deser5() throws Exception {
        assertDeser("/oasis/examples/sighting_of_observed_data.json");
    }

    @Test
    void deser6() throws Exception {
        assertDeser("/oasis/examples/threat_actor_leveraging_attack_patterns_and_malware.json");
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
