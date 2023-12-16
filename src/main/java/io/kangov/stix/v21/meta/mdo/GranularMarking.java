package io.kangov.stix.v21.meta.mdo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.common.type.IdentityRef.createIdentityRef;

@Value.Immutable 
@Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = GranularMarking.class) 
@JsonDeserialize(builder = GranularMarking.Builder.class)
@Redactable
public interface GranularMarking extends MdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends GranularMarkingImpl.Builder {
        public Builder createdByRef(String id) { return createdByRef(createIdentityRef(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(createIdentityRef(identity)); }
    }


    static GranularMarking create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static GranularMarking createGranularMarkingDm(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default GranularMarking update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotNull
    @JsonProperty("marking_ref")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
//    @JsonDeserialize(converter = MarkingDefinitionConverter.class)
    MarkingDefinition getMarkingRef();

    @Size(min = 1, message = "Must have as least 1 selector")
    @JsonProperty("selectors")
    Set<String> getSelectors();

}
