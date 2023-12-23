package io.kangov.stix.v21.external;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.TestUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class MitreTest {

    private static final Logger log = LoggerFactory.getLogger(MitreTest.class);
    private static final String BASE_PATH = "/v21/mitre/";
    private static final List<String> files = List.of(
        "enterprise-attack-1.0.json",
        "custom-object.json"
    );

    private static Stream<String> mitre_reports() {
        return files.stream();
    }

    @Inject
    Parser parser;

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource
    void mitre_reports(String filename) {
        var json1 = TestUtils.load(BASE_PATH + filename);
        var obj1  = parser.readBundle(json1);
        var json2 = parser.writeBundle(obj1);
        var obj2  = parser.readBundle(json2);
        assertThat(obj1).isEqualTo(obj2);
    }

}
