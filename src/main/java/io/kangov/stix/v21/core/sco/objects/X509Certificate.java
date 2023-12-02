package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.kangov.stix.v21.core.sco.types.X509v3Extensions;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * x509-certificate
 * <p>
 * The X509 Certificate Object represents the properties of an X.509 certificate.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "x509-certificate", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("x509-certificate")
@JsonSerialize(as = X509Certificate.class)
@JsonDeserialize(builder = X509Certificate.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "id",
    "type",
    "extensions",
    "is_self_signed",
    "hashes",
    "version",
    "serial_number",
    "signature_algorithm",
    "issuer",
    "validity_not_before",
    "validity_not_after",
    "subject",
    "subject_public_key_algorithm",
    "subject_public_key_modulus",
    "subject_public_key_exponent",
    "x509_v3_extensions"
})

//@TODO refactor BusinessRule annotation with custom method that looks up every field and does a check if there is a value
//@BusinessRule(
//    ifExp = "true",
//    thenExp = """
//        isSelfSigned().isPresent() == true || 
//        getHashes().isEmpty() == false || 
//        getVersion().isPresent() == true || 
//        getSerialNumber().isPresent() == true || 
//        getSignatureAlgorithm().isPresent() == true || 
//        getIssuer().isPresent() == true || 
//        getValidityNotBefore().isPresent() == true || 
//        getValidityNotAfter().isPresent() == true || 
//        getSubject().isPresent() == true || 
//        getSubjectPublicKeyAlgorithm().isPresent() == true || 
//        getSubjectPublicKeyModulus().isPresent() == true || 
//        getSubjectPublicKeyExponent().isPresent() == true || 
//        getX509V3Extensions().isPresent() == true
//        """,
//    errorMessage = "At least 1 property must be provided")

public interface X509Certificate extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends X509CertificateImpl.Builder {
    }

    static X509Certificate create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static X509Certificate createX509Certificate(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default X509Certificate update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }
    
    @JsonProperty("is_self_signed")
    Optional<Boolean> isSelfSigned();

    @JsonProperty("hashes")
    Map< /* @Length(min = 3, max = 256) */ /* @HashingVocab(HashingAlgorithms.class) */ String, String> getHashes();

    @JsonProperty("version")
    Optional<String> getVersion();

    @JsonProperty("serial_number")
    Optional<String> getSerialNumber();

    @JsonProperty("signature_algorithm")
    Optional<String> getSignatureAlgorithm();

    @JsonProperty("issuer")
    Optional<String> getIssuer();

    @JsonProperty("validity_not_before")
    Optional<Instant> getValidityNotBefore();

    @JsonProperty("validity_not_after")
    Optional<Instant> getValidityNotAfter();

    @JsonProperty("subject")
    Optional<String> getSubject();

    @JsonProperty("subject_public_key_algorithm")
    Optional<String> getSubjectPublicKeyAlgorithm();

    @JsonProperty("subject_public_key_modulus")
    Optional<String> getSubjectPublicKeyModulus();

    @JsonProperty("subject_public_key_exponent")
    Optional<Long> getSubjectPublicKeyExponent();

    @JsonProperty("x509_v3_extensions")
    Optional<X509v3Extensions> getX509V3Extensions();

}
