package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.kangov.stix.validation.constraints.Range;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;


/**
 * network-traffic
 * <p>
 * The Network Traffic Object represents arbitrary network traffic that
 * originates from a source and is addressed to a destination.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "network-traffic", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = NetworkTraffic.class)
@JsonDeserialize(builder = NetworkTraffic.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("network-traffic")
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "start",
    "end",
    "src_ref",
    "dst_ref",
    "src_port",
    "dst_port",
    "protocols",
    "src_byte_count",
    "dst_byte_count",
    "src_packets",
    "dst_packets",
    "ipfix",
    "src_payload_ref",
    "dst_payload_ref",
    "encapsulates_refs",
    "encapsulated_by_ref" })
@SuppressWarnings("unused")
@Introspected

public interface NetworkTraffic extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends NetworkTrafficImpl.Builder {
    }

    static NetworkTraffic create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static NetworkTraffic createNetworkTraffic(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default NetworkTraffic update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("start")
    Optional<Instant> getStart();

    @JsonProperty("end")
    Optional<Instant> getEnd();

    @JsonProperty("is_active")
    Optional<Boolean> isActive();

    @JsonProperty("src_ref")
    Optional<String> getSrcRef();

    @JsonProperty("dst_ref")
    Optional<String> getDstRef();

    @JsonProperty("src_port")
    Optional<@Range(min = 0, max = 65535 ) Integer> getSrcPort();

    @JsonProperty("dst_port")
    Optional<@Range(min = 0, max = 65535) Integer> getDstPort();

    @JsonProperty("protocols")
    Set<String> getProtocols();

    @JsonProperty("src_byte_count")
    Optional<Long> getSrcByteCount();

    @JsonProperty("dst_byte_count")
    Optional<Long> getDstByteCount();

    @JsonProperty("src_packets")
    Optional<Long> getSrcPackets();

    @JsonProperty("dst_packets")
    Optional<Long> getDstPackets();

    @JsonProperty("ipfix")
    Map<String,Object> getIpFix();

    @JsonProperty("src_payload_ref")
    Optional<String> getSrcPayloadRef();

    @JsonProperty("dst_payload_ref")
    Optional<String> getDstPayloadRef();

    @JsonProperty("encapsulates_refs")
    Set<String> getEncapsulatesRefs();

    @JsonProperty("encapsulated_by_ref")
    Optional<String> getEncapsulatedByRef();

}
