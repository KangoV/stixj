package io.kangov.stix.v21.core.sco;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import org.immutables.value.Value;

import java.util.UUID;

public interface ScoObject
        extends
            Bundleable,
            SpecVersionOptional,
            Defanged,
            Extensions,
            StixCustomProperties {


    @Value.Default
    @JsonProperty(
        value = "observable_object_key",
        access = JsonProperty.Access.WRITE_ONLY)
    default String getObservableObjectKey(){
        return UUID.randomUUID().toString();
    }

}
