package io.kangov.stix.v21.custom;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.validation.constraints.StartsWith;

import java.util.Map;

public interface CustomObject extends
    Bundleable,
    SpecVersion,
    CreatedByRef,
    GranularMarkings,
    ObjectMarkingRefs,
    ExternalReferences,
    Created {

    @Override
    @StartsWith("x-")
    String getType();

    @Override
    @StartsWith("x-")
    String getId();

    //@TODO Future enhancement to create a custom deserializer that will support the difference between x_ props and the CustomObjectProperties()
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonUnwrapped @JsonAnyGetter
    Map< /* @Length(lower = 3, upper = 250, message = "STIX Custom Properties must have a min key length of 3 and max of 250") */ String, Object> getCustomObjectProperties();

}
