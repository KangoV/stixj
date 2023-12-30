package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Archive File extension specifies a default extension for capturing
 * properties specific to archive files.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = ArchiveFileExtension.class) 
@JsonDeserialize(builder = ArchiveFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "contains_refs",
    "version",
    "comment" })
@JsonTypeName("archive-ext")
// @AllowedParents({FileCoo.class})
@SuppressWarnings("unused")
@Introspected()

public interface ArchiveFileExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ArchiveFileExtensionImpl.Builder {
    }
    
    static ArchiveFileExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static ArchiveFileExtension createArchiveFileExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default ArchiveFileExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder()).build(); }

    @JsonProperty("contains_refs")
    @NotNull
    Set<String> getContainsRefs();

    @JsonProperty("version")
    Optional<String> getVersion();

    @JsonProperty("comment")
    Optional<String> getComment();

}
