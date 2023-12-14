package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.kangov.stix.v21.core.sco.types.WindowsRegistryValueType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * windows-registry-key
 * <p>
 * The Registry Key Object represents the properties of a Windows registry key.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "windows-registry-key", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("windows-registry-key")
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonSerialize(as = WindowsRegistryKey.class)
@JsonDeserialize(builder = WindowsRegistryKey.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "key",
    "values",
    "modified",
    "creator_user_ref",
    "number_of_subkeys",
    "extensions" })

public interface WindowsRegistryKey extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends WindowsRegistryKeyImpl.Builder {
    }

    static WindowsRegistryKey create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static WindowsRegistryKey createWindowsRegistryKey(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default WindowsRegistryKey update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("key")
//    @Pattern(regexp = "^HKEY_LOCAL_MACHINE|hkey_local_machine|HKEY_CURRENT_USER|hkey_current_user|HKEY_CLASSES_ROOT|hkey_classes_root|HKEY_CURRENT_CONFIG|hkey_current_config|HKEY_PERFORMANCE_DATA|hkey_performance_data|HKEY_USERS|hkey_users|HKEY_DYN_DATA")
    @NotBlank
    String getKey();

    @JsonProperty("values")
    Set<WindowsRegistryValueType> getValues();

    @JsonProperty("modified")
    Optional<Instant> getModified();

    @JsonProperty("creator_user_ref")
    Optional<String> getCreatorUserRef(); //@TODO Must be of type user-account

    @JsonProperty("number_of_subkeys")
    Optional<Long> getNumberOfSubkeys();

}
