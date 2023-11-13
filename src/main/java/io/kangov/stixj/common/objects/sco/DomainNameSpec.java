package io.kangov.stixj.common.objects.sco;

import com.fasterxml.jackson.annotation.*;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stixj.ImmutableStyle;
import io.kangov.stixj.common.property.Id;
import io.kangov.stixj.validation.ValidateIdOnly;
import io.micronaut.serde.annotation.Serdeable;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * domain-name
 * <p>
 * The Domain Name represents the properties of a network domain name.
 *
 */

@Value.Immutable
@Value.Style(
    passAnnotations = { Serdeable.class },
    additionalJsonAnnotations = { JsonTypeName.class },
    optionalAcceptNullable = true,
//    overshadowImplementation = true,
    typeImmutable = "*",
    typeAbstract = "*Spec",
    depluralize = true,
    validationMethod = Value.Style.ValidationMethod.NONE,
    defaults = @Value.Immutable(copy = false)
)
@Serial.Version(1L)
@JsonTypeName("domain-name")
@JsonSerialize(as = DomainName.class)
@JsonDeserialize(builder = DomainName.Builder.class)
@JsonPropertyOrder({
    "id",
    "type",
    "extensions",
    "value",
    "resolves_to_refs" })
@JsonInclude(
    value = NON_EMPTY,
    content= NON_EMPTY)
@Serdeable

public interface DomainNameSpec extends Id {
    @NotBlank
    @JsonProperty("value")
    String getValue();
}
