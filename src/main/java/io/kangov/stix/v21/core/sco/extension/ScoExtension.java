package io.kangov.stix.v21.core.sco.extension;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.kangov.stix.v21.common.property.CustomProperties;
import io.kangov.stix.v21.custom.GenericCustomObject;

/**
 * Interface to tag Cyber Observable Extension classes
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true
//    defaultImpl = GenericCustomObject.class
)

public interface ScoExtension
        extends
            ScoExtensionCommonProperties,
            CustomProperties {
}
