package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * unix-account-ext
 * <p>
 * The UNIX account extension specifies a default extension for capturing the additional information
 * for an account on a UNIX system.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = UnixAccountExtension.class)
@JsonDeserialize(builder = UnixAccountExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "gid", 
    "groups",
    "home_dir",
    "shell" })
@JsonTypeName("unix-account-ext")
//@AllowedParents({UserAccountCoo.class})
//@BusinessRule(ifExp = "true", thenExp = "getGid().isPresent() == true || getGroups().isEmpty() == false || getHomeDir().isPresent() == true || getShell().isPresent() == true", errorMessage = "At least one field must be provided for Unix Account Extension")
@SuppressWarnings("unused")
@Introspected

public interface UnixAccountExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends UnixAccountExtensionImpl.Builder {
    }

    static UnixAccountExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static UnixAccountExtension createUnixAccountExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default UnixAccountExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder().from(this)).build(); }

    @JsonProperty("gid")
    Optional<Long> getGid();

    @JsonProperty("groups")
    Set<String> getGroups();

    @JsonProperty("home_dir")
    Optional<String> getHomeDir();

    @JsonProperty("shell")
    Optional<String> getShell();

}
