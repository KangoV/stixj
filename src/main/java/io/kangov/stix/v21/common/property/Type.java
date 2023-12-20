package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.immutables.value.Value;

public interface Type {

    /**
     * The type property identifies the type of STIX Object. The value of the type property MUST be the name of one of
     * the types of STIX Objects defined in sections 4, 5, 6, and 7 (e.g., indicator) or the name of a Custom Object as
     * defined by section 11.2.
     *
     * @return the type of this object
     */
    @JsonProperty("type")
    @Pattern(regexp="^-?[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*-?$")
    @Size(min=3, max=250)
    @NotBlank
    @Value.Default
    default String getType() {
        var ann = this.getClass().getAnnotation(JsonTypeName.class);
        return ann.value();
    }

}
