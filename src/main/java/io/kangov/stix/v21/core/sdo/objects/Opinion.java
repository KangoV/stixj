package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.ExternalReference;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;

/**
 * object
 * <p>
 * Identities can represent actual individuals, organizations, or groups (e.g., ACME, Inc.) as well as classes of individuals, organizations, or groups.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "object", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonTypeName("opinion")
@JsonSerialize(as = Opinion.class)
@JsonDeserialize(builder = Opinion.Builder.class)
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
    "explanation",
    "authors",
    "opinion",
    "object_refs"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Opinion extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends OpinionImpl.Builder {
        public Builder addExternalReference(UnaryOperator<ExternalReference.Builder> func) {
            return addExternalReference(func.apply(ExternalReference.builder()).build());
        }
        public Builder createdByRef(String id)         { return createdByRef(ObjectRef.createObjectRef(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(ObjectRef.createObjectRef(identity)); }

    }

    static Opinion create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Opinion createBundle(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Opinion update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    // Note for the labels attribute:
    // The list of roles that this Identity performs (e.g., CEO, Domain Administrators, Doctors, Hospital, or Retailer). No open vocabulary is yet defined for this property.

    @NotBlank
    @JsonProperty("explanation")
    @Redactable(useMask = true)
    Optional<String> getExplanation();

    @JsonProperty("authors")
    @Redactable
    Set<@NotBlank String> getAuthors();

    @NotBlank
    @JsonProperty("opinion")
    @Redactable(useMask = true)
    String getOpinion();

    @Size(min = 1, message = "Must have at least one Report object reference")
    @JsonProperty("object_refs")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
//    @JsonDeserialize( converter = BundleableObjectSetConverter.class)
    @Redactable(useMask = true)
    Set<Bundleable> getObjectRefs();

}
