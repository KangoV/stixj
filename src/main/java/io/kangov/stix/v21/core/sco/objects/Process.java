package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * process
 * <p>
 * The Process Object represents common properties of an instance of a computer
 * program as executed on an operating system.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "process", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = Process.class)
@JsonDeserialize(builder = Process.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("process")
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "is_hidden",
    "pid",
    "name",
    "created",
    "cwd",
    "arguments",
    "command_line",
    "environment_variables",
    "opened_connection_refs",
    "creator_user_ref",
    "binary_ref",
    "parent_ref",
    "child_refs" })
@SuppressWarnings("unused")
@Introspected

//@BusinessRule(
//    ifExp = "true",
//    thenExp = """
//        getExtensions().isEmpty() == false || \
//        isHidden().isPresent() == true || \
//        getPid().isPresent() == true || \
//        getName().isPresent() == true || \
//        getCreated().isPresent() == true || \
//        getCwd().isPresent() == true || \
//        getArguments().isEmpty() == false || \
//        getCommandLine().isPresent() == true || \
//        getEnvironmentVariables().isEmpty() == false || \
//        getOpenedConnectionRefs().isEmpty() == false || \
//        getCreatorUserRef().isPresent() == true || \
//        getBinaryRef().isPresent() == true || \
//        getParentRef().isPresent() == true || \
//        getChildRefs().isEmpty() == false
//        """,
//    errorMessage = "A Process Object MUST contain at least one property (other than type) from this object (or one of its extensions).")

public interface Process extends ScoObject {
    
    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ProcessImpl.Builder {
    }

    static Process create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Process createProcess(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Process update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("is_hidden")
    Optional<Boolean> isHidden();

    @JsonProperty("pid")
    Optional<Long> getPid();

    @JsonProperty("name")
    Optional<String> getName();

    @JsonProperty("created")
    Optional<Instant> getCreated();

    @JsonProperty("cwd")
    Optional<String> getCwd();

    //@TODO need better clarification in the STIX SPEC about if this should be a SET or LIST. Are duplicate arguments allowed?
    @JsonProperty("arguments")
    List<String> getArguments();

    @JsonProperty("command_line")
    Optional<String> getCommandLine();

    @JsonProperty("environment_variables")
    Map<String,String> getEnvironmentVariables();

    @JsonProperty("opened_connection_refs")
    Set<String> getOpenedConnectionRefs();

    @JsonProperty("creator_user_ref")
    Optional<String> getCreatorUserRef();

    @JsonProperty("binary_ref")
    Optional<String> getBinaryRef();

    @JsonProperty("parent_ref")
    Optional<String> getParentRef();

    @JsonProperty("child_refs")
    Set<String> getChildRefs();

}
