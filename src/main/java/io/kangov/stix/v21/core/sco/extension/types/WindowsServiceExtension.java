package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.kangov.stix.v21.enums.Vocabs;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * windows-service-ext
 * <p>
 * The Windows Service extension specifies a default extension for capturing
 * properties specific to Windows services.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = WindowsServiceExtension.class)
@JsonDeserialize(builder = WindowsServiceExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "service_name",
    "descriptions",
    "display_name",
    "group_name",
    "start_type",
    "service_dll_refs",
    "service_type",
    "service_status" })
@JsonTypeName("windows-service-ext")
//@AllowedParents({ProcessCoo.class})
@SuppressWarnings("unused")
@Introspected

public interface WindowsServiceExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends WindowsServiceExtensionImpl.Builder {
    }

    static WindowsServiceExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static WindowsServiceExtension createWindowsServiceExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default WindowsServiceExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder().from(this)).build(); }

    @JsonProperty("service_name")
    @NotBlank
    String getServiceName();

    @JsonProperty("descriptions")
    Set<String> getDescriptions();

    @JsonProperty("display_name")
    Optional<String> getDisplayName();

    @JsonProperty("group_name")
    Optional<String> getGroupName();

    @JsonProperty("start_type")
    Optional<@Vocab(Vocabs.Vocab.WINDOWS_SERVICE_START_TYPE) String> getServiceStartType();

    @JsonProperty("service_dll_refs")
    Set<String> getServiceDllRefs();

    @JsonProperty("service_type")
    Optional<@Vocab(Vocabs.Vocab.WINDOWS_SERVICE_TYPE) String> getServiceType();

    @JsonProperty("service_status")
    Optional<@Vocab(Vocabs.Vocab.WINDOWS_SERVICE_STATUS) String> getServiceStatus();

}
