package io.kangov.stix.v21.core.sco.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.property.StixCustomProperties;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * x509-certificate
 * <p>
 * The X509 Certificate Object represents the properties of an X.509 certificate.
 * Note that the X.509 v3 Extensions type is not a STIX Cyber Observables extension,
 * it is a type that describes X.509 extensions.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "x509-v3-extensions-type", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = X509v3Extensions.class)
@JsonDeserialize(builder = X509v3Extensions.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "basic_constraints", "name_constraints", "policy_constraints", "key_usage", "extended_key_usage",
        "subject_key_identifier", "authority_key_identifier", "subject_alternative_name", "issuer_alternative_name",
        "subject_directory_attributes", "crl_distribution_points", "inhibit_any_policy",
        "private_key_usage_period_not_before", "private_key_usage_period_not_after", "certificate_policies",
        "policy_mappings" })
@SuppressWarnings("unused")
@Introspected

//@JsonTypeName("x509-v3-extensions-type")
//@BusinessRule(
//    ifExp = "true",
//    thenExp = """
//        getBasicConstraints().isPresent() == true || \
//        getNameConstraints().isPresent() == true || \
//        getPolicyConstraints().isPresent() == true || \
//        getKeyUsage().isPresent() == true || \
//        getExtendedKeyUsage().isPresent() == true || \
//        getSubjectKeyIdentifier().isPresent() == true || \
//        getAuthorityKeyIdentifier().isPresent() == true || \
//        getSubjectAlternativeName().isPresent() == true || \
//        getIssuerAlternativeName().isPresent() == true || \
//        getSubjectDirectoryAttributes().isPresent() == true || \
//        getCrlDistributionPoints().isPresent() == true || \
//        getInhibitAnyPolicy().isPresent() == true || \
//        getPrivateKeyUsagePeriodNotBefore().isPresent() == true || \
//        getPrivateKeyUsagePeriodNotAfter().isPresent() == true || \
//        getCertificatePolicies().isPresent() == true || getPolicyMappings().isPresent() == true
//        """,
//    errorMessage = "At least 1 property must be provided")

public interface X509v3Extensions extends StixCustomProperties, Serializable {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends X509v3ExtensionsImpl.Builder {
    }

    static X509v3Extensions create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static X509v3Extensions createX509v3Extensions(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default X509v3Extensions update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("basic_constraints")
    Optional<String> getBasicConstraints();

    @JsonProperty("name_constraints")
    Optional<String> getNameConstraints();

    @JsonProperty("policy_constraints")
    Optional<String> getPolicyConstraints();

    @JsonProperty("key_usage")
    Optional<String> getKeyUsage();

    @JsonProperty("extended_key_usage")
    Optional<String> getExtendedKeyUsage();

    @JsonProperty("subject_key_identifier")
    Optional<String> getSubjectKeyIdentifier();

    @JsonProperty("authority_key_identifier")
    Optional<String> getAuthorityKeyIdentifier();

    @JsonProperty("subject_alternative_name")
    Optional<String> getSubjectAlternativeName();

    @JsonProperty("issuer_alternative_name")
    Optional<String> getIssuerAlternativeName();

    @JsonProperty("subject_directory_attributes")
    Optional<String> getSubjectDirectoryAttributes();

    @JsonProperty("crl_distribution_points")
    Optional<String> getCrlDistributionPoints();

    @JsonProperty("inhibit_any_policy")
    Optional<String> getInhibitAnyPolicy();

    @JsonProperty("private_key_usage_period_not_before")
    Optional<Instant> getPrivateKeyUsagePeriodNotBefore();

    @JsonProperty("private_key_usage_period_not_after")
    Optional<Instant> getPrivateKeyUsagePeriodNotAfter();

    @JsonProperty("certificate_policies")
    Optional<String> getCertificatePolicies();

    @JsonProperty("policy_mappings")
    Optional<String> getPolicyMappings();

}
