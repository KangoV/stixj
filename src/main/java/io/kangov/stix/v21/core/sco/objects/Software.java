package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * software
 * <p>
 * The Software Object represents high-level properties associated with software, including software products.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "software", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = Software.class)
@JsonDeserialize(builder = Software.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("software")
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "cpe",
    "languages",
    "vendor",
    "version" })
@SuppressWarnings("unused")
@Introspected

public interface Software extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends SoftwareImpl.Builder {
    }

    static Software create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Software createSoftware(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Software update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("name")
    @NotBlank
    String getName();
    
    /**
     *  TODO The value for this property MUST be a CPE v2.3 entry from the official NVD CPE Dictionary.
     *  regex pattern = "^cpe:2\\.3:[aho](?::(?:[a-zA-Z0-9!\"#$%&'()*+,\\\\-_.\/();<=>?@\\[\\]^`{|}~]|\\:)+){10}$"
     *  Is not valid for the @Pattern annotation (invalid escape chars)
     *  Remove @Pattern(regexp="^cpe:2\\.3:[aho]") until working solution is provided
     */
    @JsonProperty("cpe")
    Optional<String> getCpe();
    
    /**
     * TODO The value of each list member MUST be an ISO 639-2 language code.
     */
    @JsonProperty("languages")
    Set<@Pattern(regexp="^[a-z]{3}$") String> getLanguages();

    @JsonProperty("vendor")
    Optional<String> getVendor();

    @JsonProperty("version")
    Optional<String> getVersion();

}
