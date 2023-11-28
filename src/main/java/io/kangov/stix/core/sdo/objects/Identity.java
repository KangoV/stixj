package io.kangov.stix.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.common.type.ExternalReference;
import io.kangov.stix.core.sdo.SdoObject;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.*;
import java.util.function.UnaryOperator;

import static io.kangov.stix.enums.Vocabs.Vocab.IDENTITY_CLASS;
import static io.kangov.stix.enums.Vocabs.Vocab.INDUSTRY_SECTORS;

/**
 * identity
 * <p>
 * Identities can represent actual individuals, organizations, or groups (e.g., ACME, Inc.) as well as classes of individuals, organizations, or groups.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "identity", groups = { DefaultValuesProcessor.class })
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonTypeName("identity")
@JsonSerialize(as = Identity.class)
@JsonDeserialize(builder = Identity.Builder.class)
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
            addExternalReference(func.apply(ExternalReference.builder()).build());
            return this;
        }
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

    @NonNull
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("roles")
    @Redactable
    Set<String> getRoles();

    @JsonProperty("identity_class")
    @Redactable(useMask = true)
    @Vocab(IDENTITY_CLASS)
//    Optional<@Vocab(IDENTITY_CLASS) String> getIdentityClass();
    Optional<String> getIdentityClass();

    @JsonProperty("sectors")
    @Redactable
    Set<@Vocab(INDUSTRY_SECTORS) String> getSectors();

    @JsonProperty("contact_information")
    @Redactable
    Optional<String> getContactInformation();

}
