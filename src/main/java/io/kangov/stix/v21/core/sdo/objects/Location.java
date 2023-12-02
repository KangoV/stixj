package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.enums.Vocabs.Vocab.REGION;

/**
 * location
 * <p>
 * Identities can represent actual individuals, organizations, or groups (e.g., ACME, Inc.) as well as classes of individuals, organizations, or groups.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("location")
//@DefaultTypeValue(value = "location", groups = { DefaultValuesProcessor.class })
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonSerialize(as = Location.class)
@JsonDeserialize(builder = Location.Builder.class)
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
    "latitude",
    "longitude",
    "precision",
    "region",
    "country",
    "administrative_area",
    "city",
    "street_address",
    "postal_code"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Location extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends LocationImpl.Builder {}

    static Location create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Location createLocation(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Location update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("name")
    @Redactable(useMask = true)
    Optional<String> getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("longitude")
    @Redactable
    Optional<String> getLongitude();

    @JsonProperty("latitude")
    @Redactable
    Optional<String> getLatitude();

    @JsonProperty("precision")
    @Redactable
    Optional<String> getPrecision();

    @JsonProperty("region")
    @Vocab(REGION)
    @Redactable
    Optional<String> getRegion();

    @JsonProperty("country")
//    @Length(min = 2, max = 2)
    @Redactable
    Optional<String> getCountry();

    @JsonProperty("administrative_area")
    @Redactable
    Optional<String> getAdministrativeArea();

    @JsonProperty("city")
    @Redactable
    Optional<String> getCity();

    @JsonProperty("stree_address")
    @Redactable
    Optional<String> getStreetAddress();

    @JsonProperty("postal_code")
    @Redactable
    Optional<String> getPostalCode();


}