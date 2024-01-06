package io.kangov.stix.v21.core.sco.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.property.CustomProperties;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.WINDOWS_REGISTRY_DATATYPE;


/**
 * The Windows Registry Value type captures the properties of a Windows Registry Key Value.
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "windows-registry-value-type", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = WindowsRegistryValueType.class)
@JsonDeserialize(builder = WindowsRegistryValueType.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "name",
    "data",
    "data_type" })
@SuppressWarnings("unused")
@Introspected
//@JsonTypeName("windows-registry-value-type")

public interface WindowsRegistryValueType extends CustomProperties, Serializable {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends WindowsRegistryValueTypeImpl.Builder {
    }

    static WindowsRegistryValueType create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static WindowsRegistryValueType createWindowsRegistryValue(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default WindowsRegistryValueType update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @JsonProperty("name")
    @NotBlank
    String getName();

    @JsonProperty("data")
    Optional<String> getData();

    @JsonProperty("data_type")
    Optional<@Vocab(WINDOWS_REGISTRY_DATATYPE) String> getDataType();

}
