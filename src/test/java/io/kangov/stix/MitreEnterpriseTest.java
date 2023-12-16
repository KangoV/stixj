package io.kangov.stix;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.ofInputStream;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class MitreEnterpriseTest {

    private static final Logger log = LoggerFactory.getLogger(MitreEnterpriseTest.class);
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final String PATH_TEMPLATE = "https://raw.githubusercontent.com/mitre-attack/attack-stix-data/master/enterprise-attack/enterprise-attack-%s.json";
    private static final List<String> files = List.of(
        "2.0", "1.0", "3.0", "4.0", "5.0", "5.1", "5.2", "6.0", "6.1", "6.2",
        "6.3", "7.0", "7.1", "7.2", "8.0", "8.1", "8.2", "9.0", "10.0", "10.1",
        "11.0", "11.1", "11.2", "11.3", "12.0", "12.1", "13.0", "13.1", "14.0",
        "14.1"
    );

    @Inject Parser parser;
    @Inject Validator validator;

    private static final Stream<String> enterprise_attacks() {
        return reverse(files.stream()).stream();
    }

    @ParameterizedTest
    @MethodSource
    void enterprise_attacks(String input) throws Exception {
        var path = String.format(PATH_TEMPLATE, input);
        assertDeser(URI.create(path));
    }

    void assertDeser(URI uri) throws Exception {
        var json1 = load(uri);
        log.info("Testing...");
        var obj1  = parser.readBundle(json1);
        var json2 = parser.writeBundle(obj1);
        var obj2  = parser.readBundle(json2);
        assertThat(obj1).isEqualTo(obj2);
        log.info("Done");
    }

    static String load(URI uri) throws Exception {
        log.info("Retrieving bundle from: {}", uri);
        var httpResponse = HTTP_CLIENT.send(newBuilder().uri(uri).GET().build(), ofInputStream());
        try (var body = httpResponse.body()) {
            return new String(body.readAllBytes());
        }
    }

    static Deque<String> reverse(Stream<String> input) {
        return input.collect(Collector.of(
            ArrayDeque::new,
            ArrayDeque::addFirst,
            (d1, d2) -> { d2.addAll(d1); return d2; }));
    }
}
