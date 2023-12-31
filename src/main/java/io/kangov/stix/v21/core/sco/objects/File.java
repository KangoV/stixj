package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.kangov.stix.v21.core.sco.extension.*;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.*;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.HASHING_ALGORITHM;
import static io.kangov.stix.validation.constraints.Vocab.InclusionType.MUST;

/**
 * file
 * <p>
 * The File Object represents the properties of a file.
 *
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "file", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonTypeName("file")
@JsonSerialize(as = File.class)
@JsonDeserialize(builder = File.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "hashes",
    "size",
    "name",
    "name_enc",
    "magic_number_hex",
    "mime_type",
    "created",
    "modified",
    "accessed",
    "parent_directory_ref",
    "is_encrypted",
    "encryption_algorithm",
    "decryption_key",
    "contains_refs",
    "content_ref" })
@SuppressWarnings("unused")
@Introspected

//@BusinessRule(
//    ifExp = "isEncrypted().orElse(false) == false",
//    thenExp = "getEncryptionAlgorithm().isPresent() == false && getDecryptionKey().isPresent() == false",
//    errorMessage = "Encryption Algorithm and Description Key cannot be used if Encrypted equals false.")

public interface File extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends FileImpl.Builder {
    }

    static File create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static File createFile(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default File update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @JsonSerialize(using = ScoExtensionsSerializer.class)
    @JsonDeserialize(using = ScoExtensionsDeserializer.class)
    @JsonProperty("extensions")
    ScoExtensions getExtensions();

    @JsonProperty("hashes")
    Map<@Size(min=3, max=256) @Vocab(value = HASHING_ALGORITHM, inclusion = MUST) String, String> getHashes();

    @JsonProperty("size")
    Optional<@Min(1) Long> getSize();

    @JsonProperty("name")
    Optional<String> getName();

    @JsonProperty("name_enc")
    Optional<@Pattern(regexp = "^[a-zA-Z0-9/\\.+_:-]{2,250}$") String> getNameEnc();

    @JsonProperty("magic_number_hex")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getMagicNumberHex();

    //@TODO Convert this to a Vocab Validation
    @JsonProperty("mime_type")
    Optional<@Pattern(regexp = "^(application|audio|font|image|message|model|multipart|text|video)/[a-zA-Z0-9.+_-]+") String> getMimeType();

    @JsonProperty("created")
    Optional<Instant> getCTime();

    @JsonProperty("modified")
    Optional<Instant> getMTime();

    @JsonProperty("accessed")
    Optional<Instant> getATime();

    @JsonProperty("parent_directory_ref")
    Optional<ObjectRef<Directory>> getParentDirectoryRef();

    @JsonProperty("contains_refs")
    Set<ObjectRef<ScoObject>> getContainsRefs();

    @JsonProperty("content_ref")
    Optional<ObjectRef<Artifact>> getContentRef();

}
