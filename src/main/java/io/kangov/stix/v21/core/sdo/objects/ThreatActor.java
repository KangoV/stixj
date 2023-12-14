package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.IdentityRef;
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

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.*;

/**
 * threat-actor
 * <p>
 * Threat Actors are actual individuals, groups, or organizations believed to be operating with malicious intent.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonTypeName("threat-actor")
//@DefaultTypeValue(
//    value = "threat-actor",
//    groups = { DefaultValuesProcessor.class })
@JsonSerialize(as = ThreatActor.class)
@JsonDeserialize(builder = ThreatActor.Builder.class)
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
    class Builder extends ThreatActorImpl.Builder {
        public Builder createdByRef(String id) { return createdByRef(IdentityRef.create(id)); };
        public Builder createdByRef(Identity identity) { return createdByRef(IdentityRef.create(identity)); }
    }

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

    @Redactable(useMask = true)
    Set<@Vocab(THREAT_ACTOR_TYPE) String> getThreatActorTypes();

    @JsonProperty("aliases")
    @Redactable
    Set<String> getAliases();

    @JsonProperty("first_seen")
    @Redactable
    Optional<Instant> firstSeen();

    @JsonProperty("last_seen")
    @Redactable
    Optional<Instant> lastSeen();

//    @Vocab(ThreatActorRoles.class)
    @JsonProperty("roles")
    @Redactable
    Set<@Vocab(THREAT_ACTOR_ROLE) String> getRoles();

    @JsonProperty("goals")
    @Redactable
    Set<@Size(min = 1) String> getGoals();

    @JsonProperty("sophistication")
    @Redactable
    Optional<@Vocab(THREAT_ACTOR_SOPHISTICATION) String> getSophistication();

    @JsonProperty("resource_level")
    @Redactable
    Optional<@Vocab(ATTACK_RESOURCE_LEVEL) String> getResourceLevel();

    @JsonProperty("primary_motivation")
    @Redactable
    Optional<@Vocab(ATTACK_MOTIVATION) String> getPrimaryMotivation();

    @JsonProperty("secondary_motivations")
    @Redactable
    Set<@Vocab(ATTACK_MOTIVATION) String> getSecondaryMotivations();

    @JsonProperty("personal_motivations")
    @Redactable
    Set<@Vocab(ATTACK_MOTIVATION) String> getPersonalMotivations();

}
