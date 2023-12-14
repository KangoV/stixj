package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.IdentityRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sdo.types.KillChainPhase;
import io.kangov.stix.v21.enums.Vocabs;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;

/**
 * indicator
 * <p>
 * Indicators contain a pattern that can be used to detect suspicious or malicious cyber activity.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("indicator")
//@DefaultTypeValue(value = "indicator", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonSerialize(as = Indicator.class)
@JsonDeserialize(builder = Indicator.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    CREATED_BY_REF,
    CREATED,
    MODIFIED,
    REVOKED,
    LABELS,
    CONFIDENCE,
    LANG,
    EXTERNAL_REFERENCE,
    OBJECT_MARKING_REFS,
    GRANULAR_MARKINGS,
    "name",
    "description",
    "pattern",
    "valid_from",
    "valid_until",
    "kill_chain_phases"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Indicator extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends IndicatorImpl.Builder {
        public Builder killChainPhase(UnaryOperator<KillChainPhase.Builder> func) {
            return addKillChainPhase(func.apply(KillChainPhase.builder()).build());
        }
        public Builder createdByRef(String id) { return createdByRef(IdentityRef.create(id)); };
        public Builder createdByRef(Identity identity) { return createdByRef(IdentityRef.create(identity)); }
    }

    static Indicator create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Indicator createIndicator(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Indicator update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("name")
    @Redactable
    Optional<String> getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("indicator_types")
    @Redactable
    Set<@Vocab(Vocabs.Vocab.INDICATOR_TYPE) String> getIndicatorTypes();

    @NotBlank
    @JsonProperty("pattern")
    @Redactable(useMask = true)
    String getPattern();

    @NotBlank
    @JsonProperty("pattern_type")
    @Redactable
    @Vocab(Vocabs.Vocab.PATTERN_TYPE)
    @Value.Default
    default String getPatternType() {
        return "stix";
    }

    @JsonProperty("pattern_version")
    @Redactable(useMask = true)
    Optional<String> getPatternVersion();

    @NotNull
    @JsonProperty("valid_from")
    @Redactable(useMask = true)
    Instant getValidFrom();

    @JsonProperty("valid_until")
    @Redactable
    Optional<Instant> getValidUntil();

    @JsonProperty("kill_chain_phases")
    @Redactable
    Set<KillChainPhase> getKillChainPhases();

}
