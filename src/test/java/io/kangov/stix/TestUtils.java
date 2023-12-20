package io.kangov.stix;

import io.kangov.stix.v21.external.OasisExamplesTest;
import org.junit.jupiter.api.Assertions;

import java.net.URI;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.ofInputStream;

public class TestUtils {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static String load(String filename) {
        try {
            return Files.readString(Paths.get(OasisExamplesTest.class.getResource(filename).toURI()));
        } catch (Exception e) {
            Assertions.fail("Failed to read "+filename, e);
            return null;
        }
    }

    public static String httpGet(URI uri) throws Exception {
        var httpResponse = HTTP_CLIENT.send(newBuilder().uri(uri).GET().build(), ofInputStream());
        try (var body = httpResponse.body()) {
            return new String(body.readAllBytes());
        }
    }

    public static Deque<String> reverse(Stream<String> input) {
        return input.collect(Collector.of(
            ArrayDeque::new,
            ArrayDeque::addFirst,
            (d1, d2) -> { d2.addAll(d1); return d2; }));
    }

}
