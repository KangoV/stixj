package io.kangov.stixj.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stixj.validation.ValidateIdOnly;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.util.UUID;

public interface Id {

    @JsonProperty("id")
    @JsonPropertyDescription("""
        The id property uniquely identifies this object.
        For objects that support versioning, all objects with the same id are considered different versions of the \
        same object and the version of the object is identified by its modified property.
        """)
    @Pattern(regexp = "^[a-zA-Z0-9]+(-[a-zA-Z]+)*--[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Id is needed")
    @Value.Default
    default String getId() {
//        var ann = this.getClass().getAnnotation(JsonTypeName.class);
//        var type = ann.value();
        var type = "oops";
        return type + "--" + UUID.randomUUID().toString();
    }


}
