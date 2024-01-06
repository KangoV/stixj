package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * http-request-ext
 * <p>
 * The HTTP request extension specifies a default extension for capturing
 * network traffic properties specific to HTTP requests.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = HttpRequestExtension.class)
@JsonDeserialize(builder = HttpRequestExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "request_method",
    "request_value",
    "request_version",
    "request_header",
    "message_body_length",
    "message_body_data_ref" })
@JsonTypeName("http-request-ext")
//@AllowedParents({NetworkTrafficCoo.class})
@SuppressWarnings("unused")
@Introspected

public interface HttpRequestExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends HttpRequestExtensionImpl.Builder {
    }

    static HttpRequestExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static HttpRequestExtension createHttpRequestExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default HttpRequestExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder().from(this)).build(); }

    @JsonProperty("request_method")
    @NotNull
    String getRequestMethod();

    @JsonProperty("request_value")
    @NotNull
    String getRequestValue();

    @JsonProperty("request_version")
    Optional<String> getRequestVersion();

    /**
     * Currently only supports non-duplicate keys: https://github.com/oasis-tcs/cti-stix2/issues/137
     * @return
     */
    @JsonProperty("request_header")
    Map<String,String> getRequestHeader();

    //@TODO Review if this should be a long
    @JsonProperty("message_body_length")
    Optional<Long> getMessageBodyLength();

    /*
     * Must be of type artifact
     */
    @JsonProperty("message_body_data_ref")
    Optional<String> getMessageBodyDataRef();

}
