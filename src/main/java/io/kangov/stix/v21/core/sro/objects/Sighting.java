package io.kangov.stix.v21.core.sro.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import io.kangov.stix.v21.core.sdo.objects.ObservedData;
import io.kangov.stix.v21.core.sro.SroObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * sighting
 * <p>
 * A Sighting denotes the belief that something in CTI (e.g., an indicator, malware, tool, threat actor, etc.) was seen.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@ImmutableStyle
@JsonTypeName("sighting")
@JsonSerialize(as = Sighting.class)
@JsonDeserialize(builder = Sighting.Builder.class)
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
    "first_seen",
    "last_seen",
    "count",
    "sighting_of_ref",
    "observed_data_refs",
    "where_sighted_refs",
    "summary"})
@Redactable
public interface Sighting extends SroObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends SightingImpl.Builder {
        public Builder createdByRef(String id)         { return createdByRef(ObjectRef.createObjectRef(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(ObjectRef.createObjectRef(identity)); }
        public Builder sightingOfRef(String id)          { return sightingOfRef(ObjectRef.createObjectRef(id)); }
        public Builder sightingOfRef(SdoObject obj)      { return sightingOfRef(ObjectRef.createObjectRef(obj)); }
        public Builder addObservedDataRef(String id)     { return addObservedDataRef(ObjectRef.createObjectRef(id)); }
        public Builder addObservedDataRef(ObservedData obj) { return addObservedDataRef(ObjectRef.createObjectRef(obj)); }
    }

    static Sighting create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Sighting createSighting(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Sighting update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("first_seen")
    @Redactable
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen")
    @Redactable
    Optional<Instant> getLastSeen();

    @JsonProperty("count") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<@Size(max = 999999999) Integer> getCount();

    @JsonProperty("sighting_of_ref")
    @Redactable(useMask = true)
    ObjectRef<SdoObject> getSightingOfRef();

    @JsonProperty("observed_data_refs") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<ObjectRef<ObservedData>> getObservedDataRefs();

    @JsonProperty("where_sighted_refs") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<ObjectRef<SdoObject>> getWhereSightedRefs();

    @NotNull
    @JsonProperty("summary")
    @Redactable
    @Value.Default
    default Boolean isSummary(){
        return false;
    }

}
