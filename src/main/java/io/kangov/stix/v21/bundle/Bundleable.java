package io.kangov.stix.v21.bundle;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.custom.CustomObject;
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
