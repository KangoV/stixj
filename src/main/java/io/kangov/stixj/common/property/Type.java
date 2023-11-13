package io.kangov.stixj.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stixj.validation.ValidateIdOnly;
import org.immutables.value.Value;

import javax.validation.constraints.*;
import javax.validation.groups.Default;

public interface Type {

    @JsonProperty("type")
    @JsonPropertyDescription("""
        The type property identifies the type of STIX Object (SDO, Relationship Object, etc). The value of the type \
        field MUST be one of the types defined by a STIX Object (e.g., indicator) or the name of a Custom Object.
        """)
    @Pattern(regexp = "^\\-?[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\\-?$")
    @Size(min = 3, max = 250)
    @NotBlank(
        groups = { Default.class, ValidateIdOnly.class },
        message = "Type is required")
    @Value.Default
    default String getType() {
        // retrieve the type from the implementing class
//        var ann = this.getClass().getAnnotation(JsonTypeName.class);
//        var type = ann.value();
        var type = "oops"; //
        return type;
    }

}
