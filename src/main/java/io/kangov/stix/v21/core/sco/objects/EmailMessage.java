package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.kangov.stix.v21.core.sco.types.MimePart;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Email Message Object represents an instance of an email message.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "email-message", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("email-message")
@JsonSerialize(as = EmailMessage.class)
@JsonDeserialize(builder = EmailMessage.Builder.class)
@JsonPropertyOrder({
    "id",
    "type",
    "extensions",
    "is_multipart",
    "date",
    "content_type",
    "from_ref",
    "sender_ref",
    "to_refs",
    "cc_refs",
    "bcc_refs",
    "subject",
    "received_lines",
    "additional_header_fields",
    "body",
    "body_multipart",
    "raw_email_ref" })
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@SuppressWarnings("unused")
@Introspected

//@BusinessRule(
//    ifExp = "isMultipart() == true",
//    thenExp = "getBody().isPresent() == false",
//    errorMessage = "Body cannot be used if isMultipart equals true")
//@BusinessRule(
//    ifExp = "isMultipart() == false",
//    thenExp = "getBodyMultipart().isEmpty() == true",
//    errorMessage = "Body_Multipart cannot be used if isMultipart equals false")

public interface EmailMessage extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends EmailMessageImpl.Builder {
    }

    static EmailMessage create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static EmailMessage createEmailMessage(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default EmailMessage update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("is_multipart")
    Boolean isMultipart();

    @JsonProperty("date")
    Optional<Instant> getDate();

    @JsonProperty("content_type")
    Optional<String> getContentType();

    @JsonProperty("from_ref")
    Optional<String> getFromRef();

    @JsonProperty("sender_ref")
    Optional<String> getSenderRef();

    @JsonProperty("to_refs")
    Set<String> getToRefs();

    @JsonProperty("cc_refs")
    Set<String> getCcRefs();

    @JsonProperty("bcc_refs")
    Set<String> getBccRefs();

    @JsonProperty("subject")
    Optional<String> getSubject();

    @JsonProperty("received_lines")
    Set<String> getReceivedLines();

    //@TODO Should become a Multi-Map in the future https://github.com/oasis-tcs/cti-stix2/issues/138
    @JsonProperty("additional_header_fields")
    Map<String, String> getAdditionalHeaderFields();

    @JsonProperty("body")
    Optional<String> getBody();

    @JsonProperty("body_multipart")
    Set<MimePart> getBodyMultipart();

    @JsonProperty("raw_email_ref")
    Optional<String> getRawEmailRef();

}
