package io.kangov.stix.v21.core.sco;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.core.Relateable;
import org.immutables.value.Value;

import java.util.UUID;

/**
 * Represents a Cyber Security object to be embedded
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    include = JsonTypeInfo.As.EXISTING_PROPERTY)

public interface ScoDataObject extends Extensions {
}
