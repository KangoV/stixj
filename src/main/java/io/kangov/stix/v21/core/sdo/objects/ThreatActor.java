package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.*;

/**
 * threat-actor
 * <p>
 * Threat Actors are actual individuals, groups, or organizations believed to be operating with malicious intent.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonTypeName("threat-actor")
//@DefaultTypeValue(
//    value = "threat-actor",
//    groups = { DefaultValuesProcessor.class })
@JsonSerialize(as = ThreatActor.class)
@JsonDeserialize(builder = ThreatActor.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "created_by_ref",
    "created",
    "modified",
    "revoked",
    "labels",
    "external_references",
    "object_marking_refs",
    "granular_markings",
    "uses",
    "name",
    "description",
    "aliases",
    "roles",
    "goals",
    "sophistication",
    "resource_level",
    "primary_motivation",
    "secondary_motivation",
    "personal_motivations" })
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface ThreatActor extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ThreatActorImpl.Builder {}

    static ThreatActor create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static ThreatActor createThreatActor(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default ThreatActor update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @Size(min = 1, message = "Must have at least one value from threat-actor-label-ov")
    @Vocab(THREAT_ACTOR_TYPE)
    @Redactable(useMask = true)
    Set<@Size(min = 1) String> getThreatActorTypes();

    @JsonProperty("aliases")
    @Redactable
    Set<String> getAliases();

    @JsonProperty("first_seen")
    @Redactable
    Instant firstSeen();

    @JsonProperty("last_seen")
    @Redactable
    Instant lastSeen();

//    @Vocab(ThreatActorRoles.class)
    @JsonProperty("roles")
    @Redactable
    Set<String> getRoles();

    @JsonProperty("goals")
    @Redactable
    Set<@Size(min = 1) String> getGoals();

    @JsonProperty("sophistication")
    @Vocab(THREAT_ACTOR_SOPHISTICATION)
    @Redactable
    Optional<String> getSophistication();

    @JsonProperty("resource_level")
    @Vocab(ATTACK_RESOURCE_LEVEL)
    @Redactable
    Optional<String> getResourceLevel();

    @JsonProperty("primary_motivation")
    @Vocab(ATTACK_MOTIVATION)
    @Redactable
    Optional<String> getPrimaryMotivation();

    @JsonProperty("secondary_motivations")
    @Vocab(ATTACK_MOTIVATION)
    @Redactable
    Set<String> getSecondaryMotivations();

    @JsonProperty("personal_motivations")
    @JsonInclude(NON_EMPTY)
    @Vocab(ATTACK_MOTIVATION)
    @Redactable
    Set<String> getPersonalMotivations();

}
