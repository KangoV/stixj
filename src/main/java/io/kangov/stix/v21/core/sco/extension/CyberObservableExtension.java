package io.kangov.stix.v21.core.sco.extension;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.kangov.stix.v21.common.property.CustomProperties;

/**
 * Interface to tag Cyber Observable Extension classes
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    include = JsonTypeInfo.As.EXISTING_PROPERTY
)
public interface CyberObservableExtension
        extends
            CyberObservableExtensionCommonProperties,
    CustomProperties {
}
