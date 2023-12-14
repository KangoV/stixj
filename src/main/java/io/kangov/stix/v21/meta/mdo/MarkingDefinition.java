package io.kangov.stix.v21.meta.mdo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.IdentityRef;
import io.kangov.stix.v21.core.sdo.objects.*;
import io.kangov.stix.v21.meta.mdo.objects.Tlp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.common.type.IdentityRef.createIdentityRef;

/**
 * <p>Builder Required Fields:</p>
 * <ol>
 *     <li>{@link MarkingDefinition#getDefinitionType()} - (A helper is in-place for this field that will pre-populate
 *         the value based on the specific Marking Object, which makes this field essentially optional).</li>
 *     <li>{@link MarkingDefinition#getDefinition()}  - the Marking Object.  Two objects are currently supported:
 *         {@link Tlp} and {@link io.kangov.stix.v21.meta.mdo.objects.Statement}.</li>
 * </ol>
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("marking-definition")
//@DefaultTypeValue(value = "marking-definition", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = MarkingDefinition.class)
@JsonDeserialize(builder = MarkingDefinition.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "created_by_ref",
    "created",
    "external_references",
    "object_marking_refs",
    "granular_markings",
    "definition_type",
    "definition"
})
//@MarkingDefinitionTypeLimit(markingObject = TlpMarkingObject.class, markingDefinitionType = "tlp", groups = {DefaultValuesProcessor.class})
//@MarkingDefinitionTypeLimit(markingObject = StatementMarkingObject.class, markingDefinitionType = "statement", groups = {DefaultValuesProcessor.class})
@Redactable
public interface MarkingDefinition extends MdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends MarkingDefinitionImpl.Builder {
        public Builder createdByRef(String id) { return createdByRef(IdentityRef.createIdentityRef(id)); };
        public Builder createdByRef(Identity identity) { return createdByRef(createIdentityRef(identity)); }
    }

    static MarkingDefinition create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static MarkingDefinition createMarkingDefinition(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default MarkingDefinition update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("definition_type")
    String getDefinitionType();

    @NotNull
    @JsonProperty("definition")
    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "definition_type",
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY
    )
    MarkingObject getDefinition();

}
