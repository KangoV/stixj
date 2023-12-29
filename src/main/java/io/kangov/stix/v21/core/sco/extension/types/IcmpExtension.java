package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * icmp-ext
 * <p>
 * The ICMP extension specifies a default extension for capturing network
 * traffic properties specific to ICMP.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = IcmpExtension.class) @JsonDeserialize(builder = IcmpExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "icmp_type_hex", "icmp_code_hex" })
@JsonTypeName("icmp-ext")
//@AllowedParents({NetworkTrafficCoo.class})
@SuppressWarnings("unused")
@Introspected

public interface IcmpExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends IcmpExtensionImpl.Builder {
    }

    static IcmpExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static IcmpExtension createIcmpExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default IcmpExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder()).build(); }

    @JsonProperty("icmp_type_hex")
    @Pattern(regexp = "^([a-fA-F0-9]{2})+$")
    @NotNull
    String getOcmpTypeHex();

    @JsonProperty("icmp_code_hex")
    @Pattern(regexp = "^([a-fA-F0-9]{2})+$")
    @NotNull
    String getIcmpCodeHex();

}
