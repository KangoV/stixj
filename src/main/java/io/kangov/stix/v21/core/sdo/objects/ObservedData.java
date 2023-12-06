package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.validation.constraints.Range;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * observed-data
 * <p>
 * Observed data conveys information that was observed on systems and networks, such as log data or network traffic, using the Cyber Observable specification.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("observed-data")
//@DefaultTypeValue(value = "observed-data", groups = {DefaultValuesProcessor.class})
@Value.Style(
    optionalAcceptNullable = true,
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true,
    typeAbstract="",
    typeImmutable="*Impl",
    validationMethod = Value.Style.ValidationMethod.NONE, // let bean validation do it
    additionalJsonAnnotations = { JsonTypeName.class },
    depluralize = true)
@JsonSerialize(as = ObservedData.class)
@JsonDeserialize(builder = ObservedData.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
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
    "first_observed",
    "last_observed",
    "number_observed",
    "objects"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface ObservedData extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ObservedDataImpl.Builder {}

    static ObservedData create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static ObservedData createObservedData(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default ObservedData update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotNull
    @JsonProperty("first_observed")
    @Redactable(useMask = true)
    Instant getFirstObserved();

    @NotNull
    @JsonProperty("last_observed")
    @Redactable(useMask = true)
    Instant getLastObserved();

    @NotNull
    @Positive
    @JsonProperty("number_observed")
    @Redactable(useMask = true)
    @Range(min = 1, max = 999_999_999)
    @Deprecated(since = "2.1")
    Integer getNumberObserved();

    @JsonProperty("objects")
    @Redactable(useMask = true)
//    @JsonSerialize(using = CyberObservableSetFieldSerializer.class)
//    @JsonDeserialize(using = CyberObservableSetFieldDeserializer.class)
//    @Deprecated
    Set<ScoObject> getObjects();

    @JsonProperty("object_refs")
    @Redactable(useMask = true)
//    @JsonSerialize(using = CyberObservableSetFieldSerializer.class)
//    @JsonDeserialize(using = CyberObservableSetFieldDeserializer.class)
//    @Deprecated
    Set<ScoObject> getObjectRefs();

}
