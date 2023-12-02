package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.*;

public interface SpecVersionOptional {

    /**
     * Helper attribute to track the STIX Spec Version that was used for this object.
     * @return String of STIX Spec Version, example: "2.0"
     */
    @JsonProperty("spec_version")
    @JsonPropertyDescription("""
        The type property identifies the type of STIX Object (SDO, Relationship Object, etc). The value of the type \
        field MUST be one of the types defined by a STIX Object (e.g., indicator).
        """)
    @JsonIgnore
    String getSpecVersion();

}
