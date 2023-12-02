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

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "mac-addr", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("mac-addr")
@JsonSerialize(as = MacAddress.class)
@JsonDeserialize(builder = MacAddress.Builder.class)
@JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
@JsonPropertyOrder({
    "id",
    "type",
    "extensions",
    "value"})
@SuppressWarnings("unused")
@Introspected

public interface MacAddress extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends MacAddressImpl.Builder {
    }

    static MacAddress create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static MacAddress createMacAddress(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default MacAddress update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("value")
    @Pattern(regexp="^([0-9a-f]{2}[:]){5}([0-9a-f]{2})$")
    @NotBlank
    String getValue();

}
