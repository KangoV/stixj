package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.*;
import org.immutables.value.Value;

import java.util.UUID;

/**
 *
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface Id {

    @JsonProperty("id")
    @JsonPropertyDescription("""
        The id property uniquely identifies this object.
        For objects that support versioning, all objects with the same id are considered different versions of the \
        same object and the version of the object is identified by its modified property.
        """)
//    @Pattern(regexp = "^[a-zA-Z0-9]+(-[a-zA-Z]+)*--[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
//    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Id is needed")
    @Value.Default
    default String getId() {
        var ann = this.getClass().getAnnotation(JsonTypeName.class);
        var type = ann.value();
        return type + "--" + UUID.randomUUID().toString();
    }


}
