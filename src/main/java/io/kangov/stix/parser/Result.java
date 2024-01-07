package io.kangov.stix.parser;

import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.bundle.*;
import jakarta.validation.ConstraintViolation;
import org.immutables.value.Value;

import java.time.Duration;
import java.util.Set;
import java.util.function.UnaryOperator;

@Value.Immutable
@ImmutableStyle
public interface Result<T> {

    enum Type {
        BUNDLE,
        OBJECT
    }

    T get();

    Set<ConstraintViolation<?>> constrainViolations();

    Duration duration();

    Type type();

}
