package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * autonomous-system
 * <p>
 * The AS object represents the properties of an Autonomous Systems (AS).
 * 
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue( value = "autonomous-system", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonTypeName("autonomous-system")
@JsonSerialize(as = AutonomousSystem.class)
@JsonDeserialize(builder = AutonomousSystem.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "number",
    "name",
    "rir" })
@JsonInclude( value = NON_EMPTY, content= NON_EMPTY )

public interface AutonomousSystem extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends AutonomousSystemImpl.Builder {
    }

    static AutonomousSystem create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static AutonomousSystem createAutonomousSystem(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default AutonomousSystem update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @JsonProperty("number")
    Long getNumber();

    @JsonProperty("name")
    Optional<String> getName();

    @JsonProperty("rir")
    Optional<String> getRir();

}
