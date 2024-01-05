package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.ExternalReference;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.*;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.IDENTITY_CLASS;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.INDUSTRY_SECTORS;

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
@JsonTypeName("identity")
@JsonSerialize(as = Identity.class)
@JsonDeserialize(builder = Identity.Builder.class)
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
    "identity_class",
    "sectors",
    "contact_information"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Identity extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends IdentityImpl.Builder {
        public Builder addExternalReference(UnaryOperator<ExternalReference.Builder> func) {
            return addExternalReference(func.apply(ExternalReference.builder()).build());
        }
        public Builder roles(String ... roles) {
            return roles(Arrays.asList(roles));
        }
        public Builder createdByRef(String id)         { return createdByRef(ObjectRef.createObjectRef(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(ObjectRef.createObjectRef(identity)); }
    }

    static Identity create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Identity createBundle(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Identity update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    // Note for the labels attribute:
    // The list of roles that this Identity performs (e.g., CEO, Domain Administrators, Doctors, Hospital, or Retailer). No open vocabulary is yet defined for this property.

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("roles")
    @Redactable
    Set<@Size(min=2) String> getRoles();

    @JsonProperty("identity_class")
    @Redactable(useMask = true)
    Optional<@Vocab(IDENTITY_CLASS) String> getIdentityClass();

    @JsonProperty("sectors")
    @Redactable
    Set<@Vocab(INDUSTRY_SECTORS) String> getSectors();

    @JsonProperty("contact_information")
    @Redactable
    Optional<String> getContactInformation();

}
