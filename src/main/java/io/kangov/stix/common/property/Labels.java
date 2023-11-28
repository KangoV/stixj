package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.validation.constraints.Range;
import io.micronaut.core.annotation.NonNull;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 *
 */
public interface Labels {

    @NonNull
    @JsonProperty("labels") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("""
        The labels property specifies a set of terms used to describe this object. The terms are user-defined or \
        trust-group defined and their meaning is outside the scope of this specification and MAY be ignored.
        Where an object has a specific property defined in the specification for characterizing subtypes of that \
        object, the labels property MUST NOT be used for that purpose.
        For example, the Malware SDO has a property malware_types that contains a list of Malware subtypes (dropper, \
        RAT, etc.). In this example, the labels property cannot be used to describe these Malware subtypes.
        """)
    @Redactable
    Set<@Range(min = 1)  String> getLabels();

}
