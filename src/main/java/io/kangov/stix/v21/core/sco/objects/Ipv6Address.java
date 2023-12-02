package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * ipv6-addr
 * <p>
 * The IPv6 Address Object represents one or more IPv6 addresses expressed using CIDR notation.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "ipv6-addr", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("ipv6-addr")
@JsonSerialize(as = Ipv6Address.class) @JsonDeserialize(builder = Ipv6Address.Builder.class)
@JsonPropertyOrder({
    "id",
    "type",
    "extensions",
    "value",
    "resolves_to_refs",
    "belongs_to_refs"})
@SuppressWarnings("unused")
@Introspected

public interface Ipv6Address extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends Ipv6AddressImpl.Builder {
    }

    static Ipv6Address create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Ipv6Address createIpv6Address(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Ipv6Address update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    // TODO  Consider using regexp to validate:
    // http://blog.markhatton.co.uk/2011/03/15/regular-expressions-for-ip-addresses-cidr-ranges-and-hostnames/

    @JsonProperty("value")
    @NotBlank
    String getValue();

    @JsonProperty("resolves_to_refs")
    Set<String> getResolvesToRefs();

    @JsonProperty("belongs_to_refs")
    Set<String> getBelongsToRefs();

}
