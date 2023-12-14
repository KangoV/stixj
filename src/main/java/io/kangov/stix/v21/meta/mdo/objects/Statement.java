package io.kangov.stix.v21.meta.mdo.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sdo.objects.*;
import io.kangov.stix.v21.meta.mdo.MarkingObject;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.common.type.IdentityRef.createIdentityRef;

@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = Statement.class) 
@JsonDeserialize(builder = Statement.Builder.class)
@Redactable
@JsonTypeName("statement")
public interface Statement extends MarkingObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends StatementImpl.Builder {}

    static Statement create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Statement createStatement(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Statement update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("statement")
    //@Length(min = 1) 
    String getStatement();

}
