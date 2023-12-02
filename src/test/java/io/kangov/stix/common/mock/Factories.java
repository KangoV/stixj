package io.kangov.stix.common.mock;

import io.kangov.stix.Parser;
import io.kangov.stix.Stix;
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
