package io.kangov.stix.v21.bundle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.StixObject;
import io.kangov.stix.v21.common.property.SpecVersion;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static io.kangov.stix.v21.StixObject.ID;
import static io.kangov.stix.v21.StixObject.TYPE;

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
    TYPE,
    ID,
    "spec_version",
    "objects"})
@Introspected
@SuppressWarnings({"unused", "unchecked"})

public interface Bundle extends StixObject, SpecVersion {

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

    default Optional<Bundleable> find(String id) {
        return find(o -> o.getId().equals(id)).findFirst();
    }

    default Bundleable get(String id) {
        return find(id).orElseThrow(() -> new IllegalStateException("Object id "+id+" not found"));
    }

    default <T extends Bundleable> Optional<T> find(String id, Class<T> type) {
        return find(type, o -> o.getId().equals(id)).findFirst();
    }

    default <T extends Bundleable> T get(String id, Class<T> type) {
        return find(id, type).orElseThrow(() -> new IllegalStateException("Object id "+id+" not found"));
    }

    /**
     * Returns a stream that emits objects from this bundle matching the provided predicate
     *
     * @param filter the predicate to match objects in this bundle against
     * @return a stream that emits objects from this bundle matching the provided predicate
     */
    default Stream<Bundleable> find(Predicate<Bundleable> filter) {
        return getObjects().stream().filter(filter);
    }

    /**
     * Returns a stream that emits objects of {@code type} from this bundle matching the provided {@code filter}
     *
     * @param filter to match objects in this bundle against
     * @return a stream that emits objects of {@code type} from this bundle matching the provided {@code filter}
     */
    default <T extends Bundleable> Stream<T> find(Class<T> type, Predicate<T> filter) {
        return find(type).filter(filter);
    }

    /**
     * Returns a stream that emits objects from this bundle matching the provided {@code filter}
     *
     * @param type  to match objects in this bundle against
     * @return a stream that emits objects from this bundle matching the provided {@code filter}
     */
    default <T extends Bundleable> Stream<T> find(Class<T> type) {
        return (Stream<T>) find(obj -> type.isAssignableFrom(obj.getClass()));
    }
}
