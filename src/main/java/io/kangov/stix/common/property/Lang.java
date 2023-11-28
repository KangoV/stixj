package io.kangov.stix.common.property;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.redaction.Redactable;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The lang property identifies the language of the text
 * content in this object. When present, it MUST be a
 *  language code conformant to [RFC5646]. If the
 *   property is not present, then the language of the
 *  content is
 * (English).
 *  This property SHOULD be present if the object type
 *  contains translatable text properties (e.g. name,
 *  description).
 *  The language of individual fields in this object MAY
 *  be overridden by the lang property in granular
 *  markings (see section 7.2.3).
 */
public interface Lang {

    @JsonProperty("lang")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("""
        The lang property identifies the language of the text content in this object. When present, it MUST be a \
        language code conformant to [RFC5646]. If the property is not present, then the language of the content is \
        (English).
        This property SHOULD be present if the object type contains translatable text properties (e.g. name, \
        description).
        The language of individual fields in this object MAY be overridden by the lang property in granular markings.
        """)
    @Redactable
    Optional<String> getLang();

}
