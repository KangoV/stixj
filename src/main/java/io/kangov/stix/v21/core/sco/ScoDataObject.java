package io.kangov.stix.v21.core.sco;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.kangov.stix.v21.common.property.Extensions;

/**
 * Represents a Cyber Security object to be embedded
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    include = JsonTypeInfo.As.EXISTING_PROPERTY)

public interface ScoDataObject extends Extensions {
}
