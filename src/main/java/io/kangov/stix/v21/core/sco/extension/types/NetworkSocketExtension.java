package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.kangov.stix.v21.enums.Vocabs;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * socket-ext
 * <p>
 * The Network Socket extension specifies a default extension for capturing
 * network traffic properties associated with network sockets.
 *
 */
@Value.Immutable @Serial.Version(1L)
//@DefaultTypeValue(value = "socket-ext", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = NetworkSocketExtension.class)
@JsonDeserialize(builder = NetworkSocketExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "address_family",
    "is_blocking",
    "is_listening",
    "protocol_family",
    "options",
    "socket_type",
    "socket_descriptor",
    "socket_handle" })
@JsonTypeName("socket-ext")
//@AllowedParents({NetworkTrafficCoo.class})
@SuppressWarnings("unused")
@Introspected

public interface NetworkSocketExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends NetworkSocketExtensionImpl.Builder {
    }

    static NetworkSocketExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static NetworkSocketExtension createNetworkSocketExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default NetworkSocketExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder()).build(); }

    @JsonProperty("address_family")
    @NotNull
    @Vocab(Vocabs.Vocab.NETWORK_SOCKET_ADDRESS_FAMILY)
    String getAddressFamily();

    @JsonProperty("is_blocking")
    @NotNull
    Optional<Boolean> getBlocking();

    @JsonProperty("is_listening")
    @NotNull
    Optional<Boolean> getListening();

    //@TODO Should this enforce SO_* ?
    @JsonProperty("options")
    Map<String,String> getOptions();

    @JsonProperty("socket_type")
    Optional<@Vocab(Vocabs.Vocab.NETWORK_SOCKET_TYPE) String> getSocketType();

    @JsonProperty("socket_descriptor")
    Optional<@Size(min=0) Long> getSocketDescriptor();

    @JsonProperty("socket_handle")
    Optional<Long> getSocketHandle();

}
