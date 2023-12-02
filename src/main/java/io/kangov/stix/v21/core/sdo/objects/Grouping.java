package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * <p>
 * A Grouping object explicitly asserts that the referenced STIX Objects have a shared context, unlike a
 * STIX Bundle (which explicitly conveys no context). A Grouping object should not be confused with an
 * intelligence product, which should be conveyed via a STIX Report.
 * </p><p>
 * A STIX Grouping object might represent a set of data that, in time, given sufficient analysis, would mature
 * to convey an incident or threat report as a STIX Report object. For example, a Grouping could be used to
 * characterize an ongoing investigation into a security event or incident. A Grouping object could also be
 * used to assert that the referenced STIX Objects are related to an ongoing analysis process, such as
 * when a threat analyst is collaborating with others in their trust community to examine a series of
 * Groupings and Indicators. The Grouping SDO contains a list of references to SDOs, SCOs, SROs, and
 * SMOs, along with an explicit statement of the context shared by the content, a textual description, and the
 * name of the grouping.
 * </p>
 *
 * @since 2.1
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("grouping")
//@DefaultTypeValue(value = "grouping", groups = { DefaultValuesProcessor.class })
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonSerialize(as = Grouping.class)
@JsonDeserialize(builder = Grouping.Builder.class)
@JsonPropertyOrder({
    "type",
    "spec_version",
    "id",
    "created",
    "modified",
    "created_by_ref",
    "revoked",
    "labels",
    "confidence",
    "lang",
    "external_references",
    "object_marking_refs",
    "granular_markings",
    "extensions",
    "name",
    "description",
    "context",
    "object_refs"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Grouping extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends GroupingImpl.Builder {}

    static Grouping create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Grouping createGrouping(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Grouping update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("name")
    @Redactable(useMask = true)
    Optional<String> getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("context")
    @Redactable
    String getContext();

    @JsonProperty("object_refs")
    @Redactable
    List<String> getObjectRefs();

}