package io.kangov.stix.v21;


import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.custom.CustomObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import org.immutables.value.Value;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@Introspected

public interface StixObject
    extends
        Serializable,
        Id,
        Type,
        StixCustomProperties {

    String TYPE = "type";
    String ID = "id";

}
