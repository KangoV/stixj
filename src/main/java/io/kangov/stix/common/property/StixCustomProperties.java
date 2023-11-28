package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.validation.constraints.Range;
import io.kangov.stix.validation.constraints.StartsWith;

import java.util.Map;

/**
 * <p>
 * Stix Custom Properties (Deprecated as of 2.1)
 * </p><p>
 * Custom properties SHOULD be defined using the STIX Extension mechanism for property-extension as
 * described in Section 7.3.
 * </p><p>
 * There will be cases where certain information exchanges can be improved by adding properties to STIX
 * Objects and STIX Cyber Observable (SCO) Extensions that are neither specified nor reserved in this
 * specification; these properties are called Custom Properties. This section provides guidance and
 * requirements for how producers can use Custom Properties and how consumers should interpret them in
 * order to extend STIX in an interoperable manner.
 * </p>
 *
 * Deprecated as of 2.1. Use the STIX Extension mechanism
 */
public interface StixCustomProperties {

    /**
     * Custom Properties for STIX Objects.
     * Any object that supports custom properties will have a validation of the custom property prefix (typically "x_").
     * If the additional property in the JSON does not meet the StartsWith condition, then the JSON will be rejected.
     *
     * @return Map of custom properties {@code Map<String, Object>}
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonUnwrapped
    @JsonAnyGetter
    Map<@StartsWith("x_") @Range(min=3, max=250, message="STIX Custom Properties must have a min key length of 3 and max of 250") String, Object> getCustomProperties();

}
