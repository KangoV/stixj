package io.kangov.stix.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.nio.file.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.ofInputStream;

public class TestUtils {

    private static final Logger log = LoggerFactory.getLogger(TestUtils.class);
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static String loadResource(String filename) {
        try {
            var url = TestUtils.class.getResource(filename);
            if (url == null) {
                throw new IllegalArgumentException("Not found: " + filename);
            }
            var uri = TestUtils.class.getResource(filename).toURI();
            return loadResource(uri);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Failed to load: " + filename, e);
        }
    }

    public static String loadResource(URI uri) {
        var path = Paths.get(uri);
        return loadResource(path);
    }

    public static String loadResource(Path path) {
        try {
            log.debug("Loading: {}", path);
            return Files.readString(path);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load " + path, e);
        }
    }

    public static String download(String address) {
        var uri = URI.create(address);
        return download(uri);
    }

    public static String download(URI uri) {
        log.debug("Loading: {}", uri);
        try {
            var httpResponse = HTTP_CLIENT.send(newBuilder().uri(uri).GET().build(), ofInputStream());
            try (var body = httpResponse.body()) {
                return new String(body.readAllBytes());
            } catch (Exception e) {
                throw new IllegalStateException("Failed to read HTTP response from: " + uri, e);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to download: " + uri, e);
        }
    }

    public static Deque<String> reverse(Stream<String> input) {
        return input.collect(Collector.of(
            ArrayDeque::new,
            ArrayDeque::addFirst,
            (d1, d2) -> { d2.addAll(d1); return d2; }));
    }

    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        // Disable new tasks from being submitted
        pool.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                // Cancel currently executing tasks forcefully
                pool.shutdownNow();
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ex) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}
