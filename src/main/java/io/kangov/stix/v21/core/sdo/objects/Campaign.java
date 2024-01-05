package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;

/**
 * campaign
 * <p>
 * A Campaign is a grouping of adversary behavior that describes a set of malicious activities or attacks that occur over a period of time against a specific set of targets.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("campaign")
//@DefaultTypeValue(value = "campaign", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonSerialize(as = Campaign.class) @JsonDeserialize(builder = Campaign.Builder.class)
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
    "aliases",
    "first_seen",
    "last_seen",
    "objective"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Campaign extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends CampaignImpl.Builder {
        public Builder createdByRef(String id)         { return createdByRef(ObjectRef.createObjectRef(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(ObjectRef.createObjectRef(identity)); }
    }

    static Campaign create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Campaign createCampaign(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Campaign update(UnaryOperator<Builder> builder) {
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
    Set<@NotBlank String> getAliases();

    @JsonProperty("first_seen")
    @Redactable
    Optional<Instant> getFirstSeen();

    //@TODO add support to ensure that Last Seen is AFTER the First Seen value
    @JsonProperty("last_seen")
    @Redactable
    Optional<Instant> getLastSeen();

    @JsonProperty("objective")
    @Redactable
    Optional<String> getObjective();

}
