package io.kangov.stix.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.core.sdo.SdoObject;
import io.kangov.stix.core.sdo.types.KillChainPhase;
import io.kangov.stix.enums.Vocabs;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.enums.Vocabs.Vocab.INFRASTRUCTURE_TYPE;


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
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonTypeName("infrastructure")
@JsonSerialize(as = Infrastructure.class)
@JsonDeserialize(builder = Infrastructure.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "type",
    "spec_version",
    "id",
    "created",
    "modified",
    "created_by_ref",
    "revoked",
    "labels",
    "confidence",
    "lang",
    "external_references",
    "object_marking_refs",
    "granular_markings",
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
            this.addKillChainPhase(func.apply(KillChainPhase.builder()).build());
            return this;
        }
    }

    static Infrastructure create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Infrastructure createInfrastructure(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Infrastructure update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("name")
    @Redactable(useMask = true)
    Optional<String> getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("infrastructure_types")
    @Vocab(INFRASTRUCTURE_TYPE)
    @Redactable(useMask = true)
    Set<String> getInfrastructureTypes();

    @JsonProperty("aliases")
    @Redactable
    List<String> getAliases();

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
