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

import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * email-addr
 * <p>
 * The Email Address Object represents a single email address.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue( value = "email-addr", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("email-addr")
@JsonSerialize(as = EmailAddress.class)
@JsonDeserialize(builder = EmailAddress.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "value",
    "display_name",
    "belongs_to_ref"})
@JsonInclude( value = NON_EMPTY, content= NON_EMPTY)
@SuppressWarnings("unused")
@Introspected

public interface EmailAddress extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends EmailAddressImpl.Builder {
    }

    static EmailAddress create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static EmailAddress createEmailAddress(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default EmailAddress update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @NotBlank
    @JsonProperty("value")
    @Pattern(regexp="(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)")
    String getValue();

    @JsonProperty("display_name")
    Optional<String> getDisplayName();

    @JsonProperty("belongs_to_ref")
    Optional<String> getBelongsToRef();

}
