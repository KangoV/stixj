package io.kangov.stix.v21.bundle;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.Stix;
import io.kangov.stix.util.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;


public class BundleTest extends TestBases {

    private static final Logger log = LoggerFactory.getLogger(BundleTest.class);
    private static Parser parser;
    private static int count;

    private ExecutorService executor;

    @BeforeAll static void setupAll() {
        count = 10_000;
        parser = Stix.parser();
    }

    @BeforeEach
    void setup() {
        this.executor = Executors.newWorkStealingPool(6);
    }

    @Test
    void testReadPerformance() {
        var bundle = parser.read(TestBundle.BUNDLE_JSON).get();
        testPerformance(() -> parser.write(bundle), "write_bundle");
    }

    @Test
    void testWritePerformance() {
        testPerformance(() -> parser.read(TestBundle.BUNDLE_JSON), "read_bundle");
    }

    void testPerformance(Runnable runnable, String name) {
        try {
            var start = Instant.now();
            for (int i = 0; i < count; i++) {
                executor.execute(runnable);
            }
            var finish = Instant.now();
            var duration = Duration.between(start, finish);
            log.info("Completed {} iterations of [{}] in {} ms (~{}/ms)", count, name, duration.toMillis(), count/duration.toMillis());
        }
        finally {
            TestUtils.shutdownAndAwaitTermination(executor);
        }
    }

}
