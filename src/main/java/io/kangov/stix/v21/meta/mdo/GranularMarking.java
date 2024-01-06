package io.kangov.stix.v21.meta.mdo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.objects.Identity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * Whereas object markings apply to an entire STIX Object or Marking Definition and all its properties, granular
 * markings allow both data markings and language markings to be applied to individual portions of STIX Objects and
 * Marking Definitions. Granular markings are specified in the granular_markings property, which is a list of
 * granular-marking instances. Each of those instances contains a list of selectors to indicate what is marked and
 * either a reference to the marking-definition object to be applied or a language code to be applied. Granular markings
 * can be used, for example, to indicate that the name property of an indicator should be handled as TLP:GREEN, the
 * description property as TLP:AMBER, and the pattern property as TLP:RED.
 * <p>
 * The granular_markings property can also be used for language markings. To support applying both data markings and
 * language markings to an object, the granular-marking type has a choice of two properties in addition to the selector:
 * the lang property is used to apply language markings, and the marking_ref property is used to apply data markings.
 * Because each granular marking instance applies to either a language or a marking, one and only one of these
 * properties MUST be present on each instance of a granular marking.
 *
 * @see <a href="https://docs.oasis-open.org/cti/stix/v2.1/os/stix-v2.1-os.html#_robezi5egfdr">STIX 2.1: Granular Markings</a>
 */
@Value.Immutable 
@Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = GranularMarking.class) 
@JsonDeserialize(builder = GranularMarking.Builder.class)
@Redactable
public interface GranularMarking {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends GranularMarkingImpl.Builder {
        public Builder markingRef(MarkingDefinition markingDefinition) {
            return markingRef(ObjectRef.createObjectRef(markingDefinition));
        }
    }

    static GranularMarking create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static GranularMarking createGranularMarkingDm(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default GranularMarking update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }


    /**
     * The lang property identifies the language of the text identified by this marking. The value of the lang property,
     * if present, MUST be an [RFC5646] language code.
     * <p>
     * If the marking_ref property is not present, this property MUST be present. If the marking_ref property is
     * present, this property MUST NOT be present.
     *
     * @return the language of the text identified by this marking
     */
    @NotNull
    @JsonProperty("lang")
    Optional<String> lang();

    /**
     * The marking_ref property specifies the ID of the marking-definition object that describes the marking.
     * <p>
     * If the lang property is not present, this property MUST be present. If the lang property is present, this
     * property MUST NOT be present.
     *
     * @return the marking_ref property specifies the ID of the marking-definition object that describes the marking
     */
    @NotNull
    @JsonProperty("marking_ref")
    Optional<ObjectRef<MarkingDefinition>> getMarkingRef();

    /**
     * The selectors property specifies a list of selectors for content contained within the STIX Object in which this
     * property appears. Selectors MUST conform to the syntax defined below.
     * <p>
     * The marking-definition referenced in the marking_ref property is applied to the content selected by the selectors
     * in this list.
     *
     * @return a set of selectors for content contained within the STIX Object in which this property appears
     */
    @NotNull
    @Size(min = 1, message = "Must have as least 1 selector")
    @JsonProperty("selectors")
    Set<String> getSelectors();

}
