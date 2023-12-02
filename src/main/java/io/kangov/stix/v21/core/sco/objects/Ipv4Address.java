package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * ipv4-addr
 * <p>
 * The IPv4 Address Object represents one or more IPv4 addresses expressed using CIDR notation.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "ipv4-addr", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("ipv4-addr")
@JsonSerialize(as = Ipv4Address.class)
@JsonDeserialize(builder = Ipv4Address.Builder.class)
@JsonPropertyOrder({
    "id",
    "type",
    "extensions",
    "value",
    "resolves_to_refs",
    "belongs_to_refs"})
@SuppressWarnings("unused")
@Introspected

public interface Ipv4Address extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends Ipv4AddressImpl.Builder {
    }

    static Ipv4Address create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Ipv4Address createIpv4Address(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Ipv4Address update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    // TODO  Consider using regexp to validate:
    // http://blog.markhatton.co.uk/2011/03/15/regular-expressions-for-ip-addresses-cidr-ranges-and-hostnames/

    @JsonProperty("value")
    @NotNull
    String getValue();

    @JsonProperty("resolves_to_refs")
    Set<String> getResolvesToRefs();

    @JsonProperty("belongs_to_refs")
    Set<String> getBelongsToRefs();

}
