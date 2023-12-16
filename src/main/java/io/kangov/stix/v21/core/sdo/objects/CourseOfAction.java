package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.IdentityRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;

/**
 * course-of-action
 * <p>
 * A Course of Action is an action taken either to prevent an attack or to respond to an attack that is in progress. 
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("course-of-action")
//@DefaultTypeValue(value = "course-of-action", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = CourseOfAction.class)
@JsonDeserialize(builder = CourseOfAction.Builder.class)
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
    "action"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface CourseOfAction extends SdoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends CourseOfActionImpl.Builder {
        public Builder createdByRef(String id) { return createdByRef(IdentityRef.create(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(IdentityRef.create(identity)); }
    }

    static CourseOfAction create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static CourseOfAction createCourseOfAction(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default CourseOfAction update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("action")
    @Redactable(useMask = true)
    Set<@NotBlank String> getAction();

}
