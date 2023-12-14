package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Pattern;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * The Artifact Object permits capturing an array of bytes (8-bits), as a base64-encoded string, or linking to a
 * file-like payload.
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "artifact", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonTypeName("artifact")
@JsonSerialize(as = Artifact.class)
@JsonDeserialize(builder = Artifact.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "mime_type",
    "payload_bin",
    "url",
    "hashes"})
@JsonInclude(
    value = NON_EMPTY,
    content= NON_EMPTY)
@SuppressWarnings("unused")
@Introspected

//@BusinessRule(
//    ifExp = "getUrl().isPresent() == true",
//    thenExp = "getHashes().isEmpty() == false && getPayloadBin().isPresent() == false",
//    errorMessage = "When Url is used, Hashes have at least 1 value, and payload_bin cannot be used.")
//@BusinessRule(
//    ifExp = "getPayloadBin().isPresent() == true",
//    thenExp = "getUrl().isPresent() == false && getHashes().isEmpty() == true",
//    errorMessage = "When payload_bin is used, Url and hashes cannot be used.")

public interface Artifact extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends ArtifactImpl.Builder {
    }

    static Artifact create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Artifact createArtifact(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default Artifact update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("mime_type")
    Optional<@Pattern(regexp = "^(application|audio|font|image|message|model|multipart|text|video)/[a-zA-Z0-9.+_-]+") String> getMimeType();

    @JsonProperty("payload_bin")
    Optional<String> getPayloadBin();

    //@TODO review if the @Url constraint can be used instead.
    @JsonProperty("url")
    Optional<@Pattern(regexp = "^(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\\.(?:[a-z\u00a1-\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?$") String> getUrl();

    //@TODO review logic requirements for Redactable on Hash values
    @JsonProperty("hashes")
    Map</* @HashingVocab(HashingAlgorithms.class) */ String, String> getHashes();

}
