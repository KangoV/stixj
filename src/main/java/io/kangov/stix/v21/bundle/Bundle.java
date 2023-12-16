package io.kangov.stix.v21.bundle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.property.*;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.io.Serializable;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * bundle
 * <p>
 * A Bundle is a collection of arbitrary STIX Objects and Marking Definitions grouped together in a single container.
 *
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("bundle")
@ImmutableStyle
@JsonSerialize(as = Bundle.class)
@JsonDeserialize(builder = Bundle.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "spec_version",
    "objects"})
@Introspected
@SuppressWarnings({"unused", "unchecked"})

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

    /*
     * TODO: When implementing redacting, the parser will have to parse each object and then apply the redaction.
     *  For now we can just let the object mapper do it.
     */

    @Size(min = 1, message = "Must have at least 1 object in bundle")
    @JsonProperty(value = "objects" /*, access = JsonProperty.Access.WRITE_ONLY */)
    Set<Bundleable> getObjects();

    default <T extends Bundleable> Set<T> find(Class<T> type) {
        return stream(type).collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Returns a stream that emits objects from this bundle matching the provided predicate
     *
     * @param filter the predicate to match objects in this bundle against
     * @return a stream that emits objects from this bundle matching the provided predicate
     */
    default Stream<Bundleable> stream(Predicate<Bundleable> filter) {
        return getObjects().stream().filter(filter);
    }

    /**
     * Returns a stream that emits objects of {@code type} from this bundle matching the provided {@code filter}
     *
     * @param filter to match objects in this bundle against
     * @return a stream that emits objects of {@code type} from this bundle matching the provided {@code filter}
     */
    default <T extends Bundleable> Stream<T> stream(Class<T> type, Predicate<T> filter) {
        return stream(type).filter(filter);
    }

    /**
     * Returns a stream that emits objects from this bundle matching the provided {@code filter}
     *
     * @param type  to match objects in this bundle against
     * @return a stream that emits objects from this bundle matching the provided {@code filter}
     */
    default <T extends Bundleable> Stream<T> stream(Class<T> type) {
        return (Stream<T>) stream(obj -> type.isAssignableFrom(obj.getClass()));
    }
}
