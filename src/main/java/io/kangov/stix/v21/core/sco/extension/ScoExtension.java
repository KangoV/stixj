package io.kangov.stix.v21.core.sco.extension;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.v21.common.property.CustomProperties;
import io.kangov.stix.v21.custom.GenericCustomObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.immutables.value.Value;

/**
 * Interface to tag Cyber Observable Extension classes
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true
//    defaultImpl = GenericCustomObject.class
)

public interface ScoExtension extends CustomProperties {

    @NotBlank
    @JsonIgnore
    @JsonProperty("type")
    @Size(min = 3, max = 250)
    default String getType() {
        var ann = this.getClass().getAnnotation(JsonTypeName.class);
        return ann.value();
    }

}
