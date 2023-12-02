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
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Specifies a component of a multi-part email body.
 *
 */
@Value.Immutable
@Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = MimePart.class)
@JsonDeserialize(builder = MimePart.Builder.class)
@JsonPropertyOrder({
    "body",
    "body_raw_ref",
    "content_type",
    "content_disposition"})
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@SuppressWarnings("unused")
@Introspected

//@BusinessRule(
//    ifExp = "true",
//    thenExp = "getBody().isPresent() == true || getBodyRawRef().isPresent() == true",
//    errorMessage = "One of body OR body_raw_ref MUST be included.")

public interface MimePart extends StixCustomProperties, Serializable {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends MimePartImpl.Builder {
    }

    static MimePart create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static MimePart createMimePart(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default MimePart update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("body")
    Optional<String> getBody();

    @JsonProperty("body_raw_ref")
    Optional<String> getBodyRawRef();

    @JsonProperty("content_type")
    Optional<String> getContentType();

    @JsonProperty("content_disposition")
    Optional<String> getContentDisposition();

}
