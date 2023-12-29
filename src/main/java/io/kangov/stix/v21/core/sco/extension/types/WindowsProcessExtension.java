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

import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Windows Process extension specifies a default extension for capturing properties specific to Windows processes.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = WindowsProcessExtension.class)
@JsonDeserialize(builder = WindowsProcessExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "aslr_enabled",
    "dep_enabled",
    "priority",
    "owner_sid",
    "window_title",
    "startup_info" })
@JsonTypeName("windows-process-ext")
//@AllowedParents({ProcessCoo.class})
//@BusinessRule(ifExp = "isAslrEnabled().orElse(false) == true || isDepEnabled().orElse(false) == true", thenExp = "isDepEnabled().orElse(false) == false || isAslrEnabled().orElse(false) == false", errorMessage = "Dep and ASLR cannot both be enabled")
@SuppressWarnings("unused")
@Introspected

public interface WindowsProcessExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends WindowsProcessExtensionImpl.Builder {
    }

    static WindowsProcessExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static WindowsProcessExtension createWindowsProcessExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default WindowsProcessExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder()).build(); }

    //@TODO Add business rule for having at least 1 property

    @JsonProperty("aslr_enabled")
    @NotNull
    Optional<Boolean> isAslrEnabled();

    @JsonProperty("dep_enabled")
    @NotNull
    Optional<Boolean> isDepEnabled();

    @JsonProperty("priority")
    Optional<String> getPriority();

    @JsonProperty("owner_sid")
    Optional<String> getOwnerSid();

    @JsonProperty("window_title")
    Optional<String> getWindowTitle();

    @JsonProperty("startup_info")
    Map<String,String> getStartupInfo();

}
