package io.kangov.stix.bundle;


import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.common.property.*;
import io.kangov.stix.config.Factories;
import io.kangov.stix.custom.CustomObject;
import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * This interface is typically inherited by other interfaces that are considered "objects" that are part of a Bundle.
 * Thus, the name "BundleableObject".  A Bundleable Object by STIX standard is: SDO, SRO, and Marking Definition.
 * The Type field is used to determine the subtypes as registered in the {@code Factories#objectMapper()}
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true,
    defaultImpl = CustomObject.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@Introspected

public interface Bundleable
    extends
    Serializable,
    Id,
    Type,
    ObjectMarkingRefs,
    GranularMarkings {

}
