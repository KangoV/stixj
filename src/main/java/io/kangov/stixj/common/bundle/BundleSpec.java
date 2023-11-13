package io.kangov.stixj.common.bundle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stixj.Stix;
import io.kangov.stixj.common.property.Id;
import io.kangov.stixj.common.property.Type;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.Set;

/**
 * A Bundle is a collection of arbitrary STIX Objects and Marking Definitions grouped together in a single container.
 * <p>
 * A STIX Bundle Object is not a STIX Object but makes use of the type and id Common Properties. A Bundle is transient,
 * and implementations SHOULD NOT assume that other implementations will treat it as a persistent object or keep any
 * custom properties found on the bundle itself.
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "bundle", groups = {DefaultValuesProcessor.class})
@JsonTypeName("bundle")
@Value.Style(
    typeAbstract="*Spec",
    typeImmutable = "*",
    validationMethod = Value.Style.ValidationMethod.NONE,
    additionalJsonAnnotations = {JsonTypeName.class},
    depluralize = true)
//@JsonSerialize(as = Bundle.class)
//@JsonDeserialize(builder = Bundle.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "spec_version",
    "objects"})

public interface BundleSpec
        extends
            Id,
            Type {

    @Size(min = 1, message = "Must have at least 1 object in bundle")
    @JsonProperty(value = "objects", access = JsonProperty.Access.WRITE_ONLY)
    Set<Bundleable> getObjects();

    @JsonIgnore
    @Value.Lazy
    @Value.Auxiliary
    default String json() {
        try {
            return Stix.get().objectMapper().writeValueAsString(this);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot process JSON", e);
        }
    }

}
