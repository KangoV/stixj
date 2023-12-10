package io.kangov.stix.v21.core.sro.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.SdoObjectRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sro.SroObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * relationship
 * <p>
 * The Relationship object is used to link together two SDOs in order to describe how they are related to each other.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@ImmutableStyle
@JsonTypeName("relationship")
@JsonSerialize(as = Relationship.class)
@JsonDeserialize(builder = Relationship.Builder.class)
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
    "relationship_type",
    "description",
    "source_ref",
    "target_ref"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Relationship extends SroObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends RelationshipImpl.Builder {
        Builder targetRef(String id)     { return targetRef(new SdoObjectRef(id, null)); }
        Builder targetRef(SdoObject obj) { return targetRef(new SdoObjectRef(obj.getId(), obj)); }
        Builder sourceRef(String id)     { return sourceRef(new SdoObjectRef(id, null)); }
        Builder sourceRef(SdoObject obj) { return sourceRef(new SdoObjectRef(obj.getId(), obj)); }
    }

    static Relationship create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Relationship createRelationship(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Relationship update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("relationship_type")
    @Redactable(useMask = true)
    String getRelationshipType();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("source_ref")
    @Redactable(useMask = true)
    SdoObjectRef getSourceRef();

    @NotNull
    @JsonProperty("target_ref")
    @Redactable(useMask = true)
    SdoObjectRef getTargetRef();

    @JsonProperty("start_time")
    @Redactable
    Optional<Instant> getStartTime();

    @JsonProperty("stop_time")
    @Redactable
    Optional<Instant> getStopTime();

}
