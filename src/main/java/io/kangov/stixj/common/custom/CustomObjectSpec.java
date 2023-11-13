package io.kangov.stixj.common.custom;

import com.fasterxml.jackson.annotation.*;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stixj.ImmutableStyle;
import io.kangov.stixj.common.bundle.Bundleable;
import io.kangov.stixj.validation.Length;
import io.kangov.stixj.validation.StartsWith;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


@Value.Immutable
@ImmutableStyle
@Serial.Version(1L)
//@DefaultTypeValue(value = "", groups = {DefaultValuesProcessor.class})
@JsonTypeName("custom-object")
//@JsonSerialize(as = CustomObject.class)
//@JsonDeserialize(builder = CustomObject.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "created_by_ref",
    "created",
    "modified",
    "revoked",
    "labels",
    "external_references",
    "object_marking_refs",
    "granular_markings"})
@JsonInclude(
    value = NON_EMPTY,
    content= NON_EMPTY)
public interface CustomObjectSpec extends Bundleable {

    @StartsWith("x-")
    String getType();

    @StartsWith("x-")
    String getId();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonUnwrapped @JsonAnyGetter
    Map<@Length(min = 3,
        max = 250,
        message = "STIX Custom Properties must have a min key length of 3 and max of 250")
        String, Object> getCustomObjectProperties();

}
