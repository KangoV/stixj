package io.kangov.stix.v21.external;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.TestUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;
import java.util.stream.Stream;

import static java.net.http.HttpRequest.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class MitreEnterpriseTest {

    private static final Logger log = LoggerFactory.getLogger(MitreEnterpriseTest.class);
    private static final String PATH_TEMPLATE = "https://raw.githubusercontent.com/mitre-attack/attack-stix-data/master/enterprise-attack/enterprise-attack-%s.json";
    private static final List<String> files = List.of(
        "2.0", "1.0", "3.0", "4.0", "5.0", "5.1", "5.2", "6.0", "6.1", "6.2",
        "6.3", "7.0", "7.1", "7.2", "8.0", "8.1", "8.2", "9.0", "10.0", "10.1",
        "11.0", "11.1", "11.2", "11.3", "12.0", "12.1", "13.0", "13.1", "14.0",
        "14.1"
    );

//    private static final List<String> files = List.of("2.0");

    @Inject
    Parser parser;

    private static Stream<String> enterprise_attacks() {
        return TestUtils.reverse(files.stream()).stream();
    }

    @Disabled
    @ParameterizedTest
    @MethodSource
    void enterprise_attacks(String input) throws Exception {
        var path = String.format(PATH_TEMPLATE, input);
        var uri = URI.create(path);
        var json1 = TestUtils.httpGet(uri);
        var obj1  = parser.readBundle(json1);
        var json2 = parser.writeBundle(obj1);
        var obj2  = parser.readBundle(json2);
        assertThat(obj1).isEqualTo(obj2);
    }

 }
