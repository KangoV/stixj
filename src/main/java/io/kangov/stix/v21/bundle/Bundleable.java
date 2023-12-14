package io.kangov.stix.v21.bundle;


import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.StixObject;
import io.kangov.stix.v21.custom.CustomObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import org.immutables.value.Value;

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
        StixObject,
        ObjectMarkingRefs,
        GranularMarkings {

    String SPEC_VERSION = "spec_version";

    /**
     * Dictates if the object is hydrated.
     * Hydration is defined as if the Object has only a "ID" or has been properly
     * hydrated with the expected required fields
     * @return boolean
     */
    @NotNull
    @Value.Default
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    default boolean getHydrated(){
        return true;
    }

}
