package io.kangov.stix.v21.core;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class ValidationTest {

    @Inject
    Validator validator;

    @Introspected
    interface Interface {

        Optional< Integer> value();
    }

    @Introspected
    record Record(@Min(0) Optional<Integer> value) implements Interface {}

    @Disabled
    @Test
    void test() {
        var violations = validator.validate(new Record(Optional.of(-1)));
        assertThat(violations).hasSize(1);
    }


}
