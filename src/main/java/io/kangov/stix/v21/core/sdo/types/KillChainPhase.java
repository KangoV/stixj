package io.kangov.stix.v21.core.sdo.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.property.StixCustomProperties;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * kill-chain-phase
 * <p>
 * The kill-chain-phase represents a phase in a kill chain.
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = KillChainPhase.class)
@JsonDeserialize(builder = KillChainPhase.Builder.class)
@JsonPropertyOrder({
    "kill_chain_name",
    "phase_name"
})
@SuppressWarnings("unused")
@Introspected

public interface KillChainPhase extends StixCustomProperties, Serializable {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends KillChainPhaseImpl.Builder {}

    static KillChainPhase create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static KillChainPhase createKillChainPhase(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default KillChainPhase update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("kill_chain_name")
    String killChainName();

    @NotBlank
    @JsonProperty("phase_name")
    String phaseName();

}
