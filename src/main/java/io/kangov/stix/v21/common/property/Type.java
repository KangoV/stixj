package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.validation.constraints.Range;
import jakarta.validation.constraints.Pattern;
import org.immutables.value.Value;

public interface Type {

    @JsonProperty("type")
    @JsonPropertyDescription("""
        The type property identifies the type of STIX Object (SDO, Relationship Object, etc). The value of the type \
        field MUST be one of the types defined by a STIX Object (e.g., indicator) or the name of a Custom Object.
        """)
    @Pattern(regexp="^\\-?[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\\-?$")
    @Range(min=3, max=250)
//    @NotBlank(
//        groups = {
//            Default.class,
//            ValidateIdOnly.class },
//        message = "Type is required")
    @Value.Default

    default String getType() {
        var ann = this.getClass().getAnnotation(JsonTypeName.class);
        return ann.value();
    }

}
