package io.kangov.stix.v21;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.kangov.stix.v21.common.property.*;
import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@Introspected

public interface StixObject
    extends
        Serializable,
        Id,
        Type,
        CustomProperties {

    String TYPE = "type";
    String ID = "id";

}
