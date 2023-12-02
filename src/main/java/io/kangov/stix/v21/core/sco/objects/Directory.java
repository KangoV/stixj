package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * directory
 * <p>
 * The Directory Object represents the properties common to a file system directory.
 * 
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue( value = "directory", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonTypeName("directory")
@JsonSerialize(as = Directory.class)
@JsonDeserialize(builder = Directory.Builder.class)
@JsonPropertyOrder({
    "id",
    "spec_version",
    "type",
    "extensions",
    "path",
    "path_enc",
    "created",
    "modified",
    "accessed",
    "contains_refs" })
@JsonInclude( value = NON_EMPTY, content= NON_EMPTY )
@SuppressWarnings("unused")
@Introspected

public interface Directory extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends DirectoryImpl.Builder {
    }

    static Directory create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Directory createDirectory(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Directory update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("path")
    String getPath();

    @JsonProperty("path_enc")
    Optional<@Pattern(regexp = "^[a-zA-Z0-9/\\.+_:-]{2,250}$") String> getPathEnc();

    @JsonProperty("created")
    Optional<Instant> getCreated();

    @JsonProperty("modified")
    Optional<Instant> getModified();

    @JsonProperty("accessed")
    Optional<Instant> getAccessed();

    //@TODO add proper support for contains refs.  Must be Set of File or Directory types
    @JsonProperty("contains_refs")
    Set<String> getContainsRefs();

}
