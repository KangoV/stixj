package io.kangov.stix.v21.bundle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.v21.common.property.*;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.io.Serializable;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * bundle
 * <p>
 * A Bundle is a collection of arbitrary STIX Objects and Marking Definitions grouped together in a single container.
 *
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("bundle")
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonSerialize(as = Bundle.class)
@JsonDeserialize(builder = Bundle.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "spec_version",
    "objects"})
@Introspected
@SuppressWarnings("unused")

public interface Bundle
    extends
        Serializable,
    Id,
    Type,
    SpecVersion,
        StixCustomProperties {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends BundleImpl.Builder {
    }

    static Bundle create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Bundle createBundle(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Bundle update(UnaryOperator<Builder> builder) {
        return builder.apply(new Builder()).build();
    }

    @Size(min = 1, message = "Must have at least 1 object in bundle")
    @JsonProperty(value = "objects", access = JsonProperty.Access.WRITE_ONLY)
    Set<Bundleable> getObjects();

}
