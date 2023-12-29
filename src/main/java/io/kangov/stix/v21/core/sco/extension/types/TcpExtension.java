package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Pattern;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * tcp-ext
 * <p>
 * The TCP extension specifies a default extension for capturing network traffic
 * properties specific to TCP.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = TcpExtension.class)
@JsonDeserialize(builder = TcpExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "src_flags_hex",
    "dst_flags_hex" })
@JsonTypeName("tcp-ext")
//@AllowedParents({NetworkTrafficCoo.class})
//@BusinessRule(ifExp = "true", thenExp = "getSrcFlagsHex().isPresent() == true || getDstFlagsHex().isPresent() == true", errorMessage = "TCP Extension MUST contain at least one property from this extension")
@SuppressWarnings("unused")
@Introspected

public interface TcpExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends TcpExtensionImpl.Builder {
    }

    static TcpExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static TcpExtension createTcpExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default TcpExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder()).build(); }

    /**
     * Specifies the source TCP flags, as the union of all TCP flags observed
     * between the start of the traffic (as defined by the start property) and
     * the end of the traffic (as defined by the end property).
     *
     */
    @JsonProperty("src_flags_hex")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getSrcFlagsHex();

    /**
     * Specifies the destination TCP flags, as the union of all TCP flags
     * observed between the start of the traffic (as defined by the start
     * property) and the end of the traffic (as defined by the end property).
     *
     */
    @JsonProperty("dst_flags_hex")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getDstFlagsHex();

}
