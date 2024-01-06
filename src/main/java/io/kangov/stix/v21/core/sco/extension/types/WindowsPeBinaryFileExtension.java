package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.kangov.stix.v21.enums.Vocabs;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.*;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Windows PE Binary File extension specifies a default extension for
 * capturing properties specific to Windows portable executable (PE) files.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = WindowsPeBinaryFileExtension.class)
@JsonDeserialize(builder = WindowsPeBinaryFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "pe_type",
    "imphash",
    "machine_hex",
    "number_of_sections",
    "time_date_stamp",
    "pointer_to_symbol_table_hex",
    "number_of_symbols",
    "size_of_optional_header",
    "characteristics_hex",
    "file_header_hashes",
    "optional_header",
    "sections",
    "required" })
@JsonTypeName("windows-pebinary-ext")
//@AllowedParents({FileCoo.class})
@SuppressWarnings("unused")
@Introspected

public interface WindowsPeBinaryFileExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends WindowsPeBinaryFileExtensionImpl.Builder {
    }

    static WindowsPeBinaryFileExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static WindowsPeBinaryFileExtension createWindowsPeBinaryFileExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default WindowsPeBinaryFileExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder().from(this)).build(); }

    @JsonProperty("pe_type")
    @NotNull
    @Vocab(Vocabs.Vocab.WINDOWS_PEBINARY_TYPE)
    String getPeType();

    @JsonProperty("imphash")
    Optional<String> getImphash();

    @JsonProperty("machine_hex")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getMachineHex();

    @JsonProperty("number_of_sections")
    Optional<Long> getNumberOfSections();

    @JsonProperty("time_date_stamp")
    Optional<Instant> getTimeDateStamp();

    @JsonProperty("pointer_to_symbol_table_hex")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getPointerToSymbolTableHex();

    @JsonProperty("number_of_symbols")
    Optional<Long> getNumberOfSymbols();

    @JsonProperty("size_of_optional_header")
    Optional<@Min(0) Long> getSizeOfOptionalHeader();

    @JsonProperty("characteristics_hex")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getCharacteristicsHex();

    @JsonProperty("file_header_hashes")
    Map<@Size(min = 3, max = 256) @Vocab(Vocabs.Vocab.HASHING_ALGORITHM) String, String> getFileHeaderHashes();

    // TODO:
//    @JsonProperty("optional_header")
//    @JsonPropertyDescription("Specifies the PE optional header of the PE binary.")
//    Optional<WindowsPeOptionalHeaderObj> getOptionalHeader();

    // TODO:
//    @JsonProperty("sections")
//    @JsonPropertyDescription("Specifies metadata about the sections in the PE file.")
//    Set<WindowsPeSectionObj> getSections();

}
