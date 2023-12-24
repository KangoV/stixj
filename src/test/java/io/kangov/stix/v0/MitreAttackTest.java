package io.kangov.stix.v0;

import io.kangov.stix.util.TestBases;
import io.kangov.stix.parser.Parser;
import io.kangov.stix.util.TestUtils;
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
public class MitreAttackTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(MitreAttackTest.class);
    private static final String PATH_TEMPLATE_V21 = "https://raw.githubusercontent.com/mitre-attack/attack-stix-data/master/%1$s/%1$s.json";
    private static final String PATH_TEMPLATE_V20 = "https://raw.githubusercontent.com/mitre/cti/master/%1$s/%1$s.json";
    private static final List<String> types = List.of(
        "enterprise-attack",
        "ics-attack",
        "mobile-attack"
    );

//    private static final List<String> files = List.of("2.0");

    @Inject
    Parser parser;

    private static Stream<String> types() {
        return types.stream();
    }

    //    @Disabled
    @ParameterizedTest
    @MethodSource("types")
    void v20(String input) throws Exception {
        var bundleJson = TestUtils.download(String.format(PATH_TEMPLATE_V20, input));
        checkBundle(parser, bundleJson);
    }

    //    @Disabled
    @ParameterizedTest
    @MethodSource("types")
    void v21(String input) throws Exception {
        var bundleJson = TestUtils.download(String.format(PATH_TEMPLATE_V21, input));
        checkBundle(parser, bundleJson);
    }

 }
