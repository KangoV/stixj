package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.IdentityRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sdo.types.KillChainPhase;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.INFRASTRUCTURE_TYPE;


/**
 * malware
 * <p>
 * Malware is a type of TTP that is also known as malicious code and malicious software, refers to a program that is inserted into a system,
 * usually covertly, with the intent of compromising the confidentiality, integrity, or availability of the victim's data, applications,
 * or operating system (OS) or of otherwise annoying or disrupting the victim.
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "infrastructure", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonTypeName("infrastructure")
@JsonSerialize(as = Infrastructure.class)
@JsonDeserialize(builder = Infrastructure.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
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
    "infrastructure_types",
    "aliases",
    "kill_chain_phases",
    "first_seen",
    "last_seen",})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Infrastructure extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends InfrastructureImpl.Builder {
        public Builder killChainPhase(UnaryOperator<KillChainPhase.Builder> func) {
            return addKillChainPhase(func.apply(KillChainPhase.builder()).build());
        }
        public Builder createdByRef(String id) { return createdByRef(IdentityRef.create(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(IdentityRef.create(identity)); }
    }

    static Infrastructure create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Infrastructure createInfrastructure(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Infrastructure update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }


    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("infrastructure_types")
    @Redactable(useMask = true)
    Set<@Vocab(INFRASTRUCTURE_TYPE) String> getInfrastructureTypes();

    @JsonProperty("aliases")
    @Redactable
    List<@NotBlank String> getAliases();

    @JsonProperty("kill_chain_phases")
    @Redactable
    Set<KillChainPhase> getKillChainPhases();

    @JsonProperty("first_seen")
    @Redactable(useMask = true)
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen")
    @Redactable(useMask = true)
    Optional<Instant> getLastSeen();

}
