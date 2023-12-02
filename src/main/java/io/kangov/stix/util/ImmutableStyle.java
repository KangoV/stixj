package io.kangov.stix.util;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.immutables.value.Value;

import java.lang.annotation.*;

/**
 * A style annotation that can be used on interfaces annotated via the Immutable
 * library to alter how the creation process is performed.
 * <p>
 * The overshadowImplementation = true style attribute makes sure that build()
 * will be declared to return abstract value type Person, not the implementation
 * ImmutablePerson, following metaphor: implementation type will be
 * "overshadowed" by abstract value type.
 * <p>
 * Essentially, the generated class becomes implementation detail without much
 * boilerplate which is needed to fully hide implementation behind user-written
 * code.
 *
 * @since 0.5.0
 */
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@Value.Style(
    passAnnotations = { Introspected.class },
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true,
    jakarta = true,
    depluralizeDictionary = {
        "hash:hashes",
        "alias:aliases"
    }
)
public @interface ImmutableStyle { // empty
}
