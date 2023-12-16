package io.kangov.stix.v21.common.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.property.StixCustomProperties;
import io.kangov.stix.validation.constraints.Range;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * external-reference
 * <p>
 * External references are used to describe pointers to information represented outside STIX.
 * 
 */

@Value.Immutable
@Serial.Version(1L)
@JsonSerialize(as = ExternalReference.class)
@JsonDeserialize(builder = ExternalReference.Builder.class)
@JsonPropertyOrder({
	"source_name",
    "description",
    "url",
    "hashes",
    "external_id"
})
@ImmutableStyle

public interface ExternalReference extends StixCustomProperties, Serializable {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ExternalReferenceImpl.Builder {
    }

    static ExternalReference create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static ExternalReference createBundle(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default ExternalReference update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    //    @NotBlank
    @JsonProperty("source_name")
    String getSourceName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @JsonProperty("url")
    Optional<String> getUrl();

    @JsonProperty("hashes")
    Map<@Range(min = 3, max = 256) /* @HashingVocab(HashingAlgorithms.class) */ String, String> getHashes();

    @JsonProperty("external_id")
    Optional<String> getExternalId();

}
