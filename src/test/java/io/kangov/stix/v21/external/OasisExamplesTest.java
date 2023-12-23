package io.kangov.stix.v21.external;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.TestUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class OasisExamplesTest {

    private static final Logger log = LoggerFactory.getLogger(OasisExamplesTest.class);
    private static final String BASE_PATH = "/v21/oasis/examples/";
    private static final List<String> files = List.of(
        "defining_campaigns_vs_threat_actors_vs_intrusion_sets.json",
        "identifiying_a_threat_actor_profile.json",
        "indicator_for_malicious_url.json",
        "malware_indicator_for_file_hash.json",
        "sighting_of_an_indicator.json",
        "sighting_of_observed_data.json",
        "threat_actor_leveraging_attack_patterns_and_malware.json"
    );

    private static Stream<String> oasis_examples() {
        return files.stream();
    }

    @Inject
    Parser parser;
    @Inject Validator validator;

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource
    void oasis_examples(String filename) {
        var json1 = TestUtils.load(BASE_PATH + filename);
        var obj1  = parser.readBundle(json1);
        var json2 = parser.writeBundle(obj1);
        var obj2  = parser.readBundle(json2);
        assertThat(obj1).isEqualTo(obj2);
    }

}
