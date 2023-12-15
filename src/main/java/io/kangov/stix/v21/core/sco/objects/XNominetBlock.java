package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * directory
 * <p>
 * The Directory Object represents the properties common to a file system directory.
 * 
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue( value = "directory", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonTypeName("x-nominet-block")
@JsonSerialize(as = XNominetBlock.class)
@JsonDeserialize(builder = XNominetBlock.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "qname",
    "qtype",
    "qclass",
    "src_ip_network_type",
    "src_ip",
    "src_port",
    "rpz_range",
    "rpz_range_matched",
    "rpz_zone",
    "dns_type" })
@JsonInclude( value = NON_EMPTY, content= NON_EMPTY )
@SuppressWarnings("unused")
@Introspected

public interface XNominetBlock extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends XNominetBlockImpl.Builder {
    }

    static XNominetBlock create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static XNominetBlock createXNominetBlock(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default XNominetBlock update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("qname")
    Optional<String> getQname();

    @JsonProperty("qtype")
    Optional<String> getQType();

    @JsonProperty("qclass")
    Optional<String> getQClass();

    @JsonProperty("src_ip_network_type")
    Optional<String> getSourceIPNetworkType();

    @JsonProperty("src_ip")
    Optional<String> getSourceIP();

    @JsonProperty("src_port")
    Optional<Integer> getSourcePort();

    @JsonProperty("rpz_range")
    Optional<String> getRPZRange();

    @JsonProperty("rpz_range_matched")
    Optional<String> getRPZRangeMatched();

    @JsonProperty("rpz_zone")
    Optional<String> getRPZZone();

    @JsonProperty("dns_type")
    Optional<String> getDNSType();


}
