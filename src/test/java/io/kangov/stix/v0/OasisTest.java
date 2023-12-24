package io.kangov.stix.v0;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestBases;
import io.kangov.stix.util.TestUtils;
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
public class OasisTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(OasisTest.class);
    private static final String BASE_PATH = "/v21/oasis/";
    private static final List<String> files = List.of(
        "examples/defining_campaigns_vs_threat_actors_vs_intrusion_sets.json",
        "examples/identifiying_a_threat_actor_profile.json",
        "examples/indicator_for_malicious_url.json",
        "examples/malware_indicator_for_file_hash.json",
        "examples/sighting_of_an_indicator.json",
        "examples/sighting_of_observed_data.json",
        "examples/threat_actor_leveraging_attack_patterns_and_malware.json",
        "reports/damballa/imddos_botnet.json",
        "reports/fireeye/poison_ivy.json",
        "reports/mandiant/apt1.json"
    );

    private static Stream<String> oasis_examples() {
        return files.stream();
    }

    @Inject Parser parser;
    @Inject Validator validator;

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource
    void oasis_examples(String filename) {
        var jsonBundle = TestUtils.loadResource(BASE_PATH + filename);
        checkBundle(parser, jsonBundle);
    }

}
