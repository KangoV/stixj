package io.kangov.stix.v21.meta.mdo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.common.type.ObjectRef.createObjectRef;

/**
 *
 * The marking-definition object represents a specific marking. Data markings typically represent handling or sharing
 * requirements for data and are applied in the object_marking_refs and granular_markings properties on STIX Objects,
 * which reference a list of IDs for marking-definition objects.
 * <p>
 * Two marking definition types are defined in this specification: TLP, to capture TLP markings, and Statement, to
 * capture text marking statements. In addition, it is expected that the FIRST Information Exchange Policy (IEP) will be
 * included in a future version once a machine-usable specification for it has been defined.
 * <p>
 * Unlike other STIX Objects, Marking Definition objects cannot be versioned because it would allow for indirect changes
 * to the markings on a STIX Object. For example, if a Statement marking is changed from "Reuse Allowed" to
 * "Reuse Prohibited", all STIX Objects marked with that Statement marking would effectively have an updated marking
 * without being updated themselves. Instead, a new Statement marking with the new text should be created and the marked
 * objects updated to point to the new marking.
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
        public Builder createdByRef(String id)         { return createdByRef(createObjectRef(id, Identity.class)); }
        public Builder createdByRef(Identity identity) { return createdByRef(createObjectRef(identity)); }
    }

    static MarkingDefinition create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static MarkingDefinition createMarkingDefinition(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default MarkingDefinition update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    /**
     * The definition_type property identifies the type of Marking Definition. The value of the definition_type property
     * SHOULD be one of {@code statement} or {@code tlp}
     *
     * @return the definition type
     */
    @NotBlank
    @JsonProperty("definition_type")
    String getDefinitionType();

    /**
     * The definition property contains the marking object itself (e.g., the TLP marking as defined in section 7.2.1.4,
     * the Statement marking as defined in section 7.2.1.3, or some other marking definition defined elsewhere).
     *
     * @return the marking object
     */
    @NotNull
    @JsonProperty("definition")
    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "definition_type",
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY
    )
    MarkingObject getDefinition();

}
