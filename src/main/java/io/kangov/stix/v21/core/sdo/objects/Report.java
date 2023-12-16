package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.type.BundleableRef;
import io.kangov.stix.v21.common.type.IdentityRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.*;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.REPORT_TYPE;

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
@ImmutableStyle
@JsonDeserialize(builder = Report.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    CREATED_BY_REF,
    CREATED,
    MODIFIED,
    REVOKED,
    LABELS,
    CONFIDENCE,
    LANG,
    EXTERNAL_REFERENCE,
    OBJECT_MARKING_REFS,
    GRANULAR_MARKINGS,
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
    class Builder extends ReportImpl.Builder {
        public Builder createdByRef(String id) { return createdByRef(IdentityRef.create(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(IdentityRef.create(identity)); }
        public Builder addObjectRef(Bundleable obj) { return addObjectRef(BundleableRef.create(obj)); }
        public Builder addObjectRef(String str) { return addObjectRef(BundleableRef.create(str)); }
    }

    static Report create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Report createReport(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Report update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("report_types")
    @Redactable(useMask = true)
    Set<@Vocab(REPORT_TYPE) String> reportTypes();

    @NotNull
    @JsonProperty("published")
    @Redactable(useMask = true)
    Instant getPublished();

    @Size(min = 1, message = "Must have at least one Report object reference")
    @JsonProperty("object_refs")
    @Redactable(useMask = true)
    Set<BundleableRef> getObjectRefs();

}
