package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sdo.types.KillChainPhase;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "attack-pattern", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("attack-pattern")
@JsonSerialize(as = AttackPattern.class)
@JsonDeserialize(builder = AttackPattern.Builder.class)
@JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
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
    "kill_chain_phases"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface AttackPattern extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends AttackPatternImpl.Builder {
        public Builder killChainPhase(UnaryOperator<KillChainPhase.Builder> func) {
            return addKillChainPhase(func.apply(KillChainPhase.builder()).build());
        }
        public Builder createdByRef(String id)         { return createdByRef(ObjectRef.createObjectRef(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(ObjectRef.createObjectRef(identity)); }
    }


    static AttackPattern create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static AttackPattern createAttackPattern(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default AttackPattern update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("aliases")
    @Redactable
    Set<@NotBlank String> getAliases();

    @JsonProperty("kill_chain_phases")
    @Redactable
    Set<@NotNull KillChainPhase> getKillChainPhases();

}