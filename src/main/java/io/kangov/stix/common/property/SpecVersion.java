package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.immutables.value.Value;


public interface SpecVersion {

    @JsonProperty("spec_version")
    @JsonPropertyDescription("""
        The version of the STIX specification used to represent this object.
        The value of this property MUST be 2.1 for STIX Objects defined according to this specification.
        If objects are found where this property is not present, the implicit value for all STIX Objects other than \
        SCOs is 2.0. Since SCOs are now top-level objects in STIX 2.1, the default value for SCOs is 2.1.
        """)
//    @NotBlank
    @Value.Default // Lazy will NOT work
    default String getSpecVersion(){
        return "2.1";
    }

}
