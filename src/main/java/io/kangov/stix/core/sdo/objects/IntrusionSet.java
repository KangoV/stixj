package io.kangov.stix.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.core.sdo.SdoObject;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.enums.Vocabs.Vocab.ATTACK_MOTIVATION;
import static io.kangov.stix.enums.Vocabs.Vocab.ATTACK_RESOURCE_LEVEL;

/**
 * intrusion-set
 * <p>
 * An Intrusion Set is a grouped set of adversary behavior and resources with common properties that is believed to be orchestrated by a single organization.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("intrusion-set")
//@DefaultTypeValue(
//    value = "intrusion-set",
//    groups = { DefaultValuesProcessor.class })
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonSerialize(as = IntrusionSet.class)
@JsonDeserialize(builder = IntrusionSet.Builder.class)
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
    "name",
    "description",
    "aliases",
    "first_seen",
    "last_seen",
    "goals",
    "resource_level",
    "primary_motivation",
    "secondary_motivation"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface IntrusionSet extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends IntrusionSetImpl.Builder {}

    static IntrusionSet create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static IntrusionSet createIntrusionSet(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default IntrusionSet update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
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
    Set<String> getAliases();

    @JsonProperty("first_seen")
    @Redactable
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen")
    @Redactable
    Optional<Instant> getLastSeen();

    @JsonProperty("goals")
    @Redactable
    Set<String> getGoals();

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

}
