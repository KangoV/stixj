package io.kangov.stixj;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.micronaut.serde.annotation.Serdeable;
import org.immutables.value.Value;

import java.lang.annotation.*;


@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@Value.Style(
    passAnnotations = { Serdeable.class },
    additionalJsonAnnotations = { JsonTypeName.class },
    optionalAcceptNullable = true,
//    overshadowImplementation = true,
    typeImmutable = "*",
    typeAbstract = "*Spec",
    depluralize = true,
    validationMethod = Value.Style.ValidationMethod.NONE,
    defaults = @Value.Immutable(copy = false)
    )
public @interface ImmutableStyle { // empty
}
