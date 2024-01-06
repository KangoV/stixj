package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * domain-name
 * <p>
 * The Domain Name represents the properties of a network domain name.
 *
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue( value = "domain-name", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("domain-name")
@JsonSerialize(as = DomainName.class)
@JsonDeserialize(builder = DomainName.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "value",
    "resolves_to_refs" })
@JsonInclude(
    value = NON_EMPTY,
    content= NON_EMPTY)
@SuppressWarnings("unused")
@Introspected

public interface DomainName extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends DomainNameImpl.Builder {
    }

    static DomainName create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static DomainName createDomainName(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default DomainName update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @NotBlank
    @JsonProperty("value")
    String getValue();

    @JsonProperty("resolves_to_refs")
    Set<String> getResolvesToRefs();
}
