package io.kangov.stix.v21.core.sco.extension;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.immutables.value.Value;

@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface CyberObservableExtensionCommonProperties {

    /**
     * This property is used for generation of the dictionary during serialization, and used as the "Type" mapping value for polymorphic when deserializing.
     * @return
     */
    @NotBlank
    @JsonIgnore
    @JsonProperty("type")
    @Size(min = 3, max = 250)
    String getType();

}
