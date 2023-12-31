package io.kangov.stix.v21.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sdo.SdoObject;
import io.kangov.stix.v21.core.sdo.types.KillChainPhase;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.core.sdo.SdoObject.*;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.TOOL_TYPE;

/**
 * tool
 * <p>
 * Tools are legitimate software that can be used by threat actors to perform attacks.
 * This SDO MUST NOT be used to characterize malware. 
 * Further, Tool MUST NOT be used to characterize tools used as part of a course of action in response to an attack.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@JsonTypeName("tool")
//@DefaultTypeValue(value = "tool", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonSerialize(as = Tool.class)
@JsonDeserialize(builder = Tool.Builder.class)
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
    "kill_chain_phases",
    "tool_version"})
@Redactable
@SuppressWarnings("unused")
@Introspected

public interface Tool extends SdoObject {
    
    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ToolImpl.Builder {
        public Builder killChainPhase(UnaryOperator<KillChainPhase.Builder> func) {
            this.addKillChainPhase(func.apply(KillChainPhase.builder()).build());
            return this;
        }
        public Builder createdByRef(String id)         { return createdByRef(ObjectRef.createObjectRef(id)); }
        public Builder createdByRef(Identity identity) { return createdByRef(ObjectRef.createObjectRef(identity)); }
    }

    static Tool create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Tool createTool(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Tool update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("tool_types")
    @Redactable
    Set<@Vocab(TOOL_TYPE) String> getToolTypes();

    @JsonProperty("aliases")
    @Redactable
    Set<String> getAliases();

    @JsonProperty("kill_chain_phases")
    @Redactable
    Set<KillChainPhase> getKillChainPhases();

    @JsonProperty("tool_version")
    @Redactable
    Optional<String> getToolVersion();

}
