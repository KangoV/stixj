package io.kangov.stix.util.mock;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import net.andreinc.mockneat.MockNeat;

@Factory
public class Factories {

    @Bean
    MockNeat mockNeat() {
        return MockNeat.threadLocal();
    }

}
