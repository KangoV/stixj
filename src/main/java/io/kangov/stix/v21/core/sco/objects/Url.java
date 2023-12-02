package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * url
 * <p>
 * The URL Object represents the properties of a uniform resource locator (URL).
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "url", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("url")
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonSerialize(as = Url.class)
@JsonDeserialize(builder = Url.Builder.class)
@JsonPropertyOrder({
    "id",
    "type",
    "extensions",
    "value"})
@SuppressWarnings("unused")
@Introspected

public interface Url extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends UrlImpl.Builder {
    }

    static Url create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Url createUrl(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Url update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    /**
     * The value of this property MUST conform to [RFC3986], more specifically section 1.1.3
     * with reference to the definition for "Uniform Resource Locator"
     * (Required)
     *
     */
    //@TODO can this be changed from @Pattern to @URL?
    @JsonProperty("value")
    @Pattern(regexp="^([a-zA-Z][a-zA-Z0-9+.-]*):(?:\\/\\/((?:(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:]|%[0-9a-fA-F]{2})*))(\\3)@)?(?=((?:\\[?(?:::[a-fA-F0-9]+(?::[a-fA-F0-9]+)?|(?:[a-fA-F0-9]+:)+(?::[a-fA-F0-9]+)+|(?:[a-fA-F0-9]+:)+(?::|(?:[a-fA-F0-9]+:?)*))\\]?)|(?:[a-zA-Z0-9-._~!$&'()*+,;=]|%[0-9a-fA-F]{2})*))\\5(?::(?=(\\d*))\\6)?)(\\/(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/]|%[0-9a-fA-F]{2})*))\\8)?|(\\/?(?!\\/)(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/]|%[0-9a-fA-F]{2})*))\\10)?)(?:\\?(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/?]|%[0-9a-fA-F]{2})*))\\11)?(?:#(?=((?:[a-zA-Z0-9-._~!$&'()*+,;=:@\\/?]|%[0-9a-fA-F]{2})*))\\12)?$")
    @NotBlank
    String getValue();

}
