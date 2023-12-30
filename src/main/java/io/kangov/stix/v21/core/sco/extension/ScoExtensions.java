package io.kangov.stix.v21.core.sco.extension;

import io.kangov.stix.util.ImmutableStyle;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@Value.Immutable
@Serial.Version(1L)
@ImmutableStyle
public interface ScoExtensions {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ScoExtensionsImpl.Builder {
    }

    static ScoExtensions create(ScoExtension ext) {
        return create(b -> b.addExtension(ext));
    }

    static ScoExtensions create(ScoExtension ... extensions) {
        return create(b -> b.addExtensions(extensions));
    }

    static ScoExtensions create(Iterable<ScoExtension> extensions) {
        return create(b -> b.addAllExtensions(extensions));
    }

    static ScoExtensions create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static ScoExtensions createScoExtensions(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default ScoExtensions update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    Set<ScoExtension> getExtensions();

    /**
     * Returns a stream that emits objects from this bundle matching the provided predicate
     *
     * @param filter the predicate to match objects in this bundle against
     * @return a stream that emits objects from this bundle matching the provided predicate
     */
    default Stream<ScoExtension> stream(Predicate<ScoExtension> filter) {
        return getExtensions().stream().filter(filter);
    }

    /**
     * Returns a stream that emits objects of {@code type} from this bundle matching the provided {@code filter}
     *
     * @param filter to match objects in this bundle against
     * @return a stream that emits objects of {@code type} from this bundle matching the provided {@code filter}
     */
    default <T extends ScoExtension> Stream<T> stream(Class<T> type, Predicate<T> filter) {
        return stream(type).filter(filter);
    }

    /**
     * Returns a stream that emits objects from this bundle matching the provided {@code filter}
     *
     * @param type to match objects in this bundle against
     * @return a stream that emits objects from this bundle matching the provided {@code filter}
     */
    default <T extends ScoExtension> Stream<T> stream(Class<T> type) {
        return (Stream<T>) stream(obj -> type.isAssignableFrom(obj.getClass()));
    }

}
