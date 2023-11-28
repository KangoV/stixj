package io.kangov.stix.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.bundle.Bundleable;
import io.kangov.stix.core.sdo.SdoObject;
import io.kangov.stix.enums.Vocabs;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.*;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.enums.Vocabs.Vocab.REPORT_TYPE;

/**
 * report
 * <p>
 * Reports are collections of threat intelligence focused on one or more topics, such as a 
 * description of a threat actor, malware, or attack technique, including context and related details.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("report")
//@DefaultTypeValue(value = "report", groups = {DefaultValuesProcessor.class})
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonSerialize(as = Report.class)
@JsonDeserialize(builder = Report.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "created_by_ref",
    "created",
    "modified",
    "revoked",
    "labels",
    "external_references",
    "object_marking_refs",
    "granular_markings",
    "name",
    "description",
    "published",
    "object_refs"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Report extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ReportImpl.Builder {}

    static Report create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Report createReport(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Report update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @Override
    @Redactable(useMask = true)
    @Vocab(REPORT_TYPE)
    @Size(min = 1)
    Set<String> getLabels();

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("published")
    @Redactable(useMask = true)
    Instant getPublished();

    @Size(min = 1, message = "Must have at least one Report object reference")
    @JsonProperty("object_refs")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
//    @JsonDeserialize( converter = BundleableObjectSetConverter.class)
    @Redactable(useMask = true)
    Set<Bundleable> getObjectRefs();

}
