package io.kangov.stixj.common.bundle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.kangov.stixj.Stix;
import io.kangov.stixj.common.custom.CustomObject;
import io.kangov.stixj.common.property.Id;
import io.kangov.stixj.common.property.Type;
import io.micronaut.serde.annotation.Serdeable;
import org.immutables.value.Value;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * This interface is typically inherited by other interfaces that are considered "objects" that are part of a Bundle.
 * Thus the name "Bundleable".  A Bundleable Object by STIX standard is: SDO, SRO, and Marking Definition.
 * The Type field is used to determine the subtypes as registered in the {@code io.kangov.stix.json.StixParsers}
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    defaultImpl = CustomObject.class
//    visible = true
)
@JsonInclude(
    value = NON_EMPTY,
    content= NON_EMPTY
)
@Serdeable
public interface Bundleable
        extends
            Serializable,
            Id
//            Type
{
}
