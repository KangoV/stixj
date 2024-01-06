package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
/**
 * The PDF file extension specifies a default extension for capturing properties
 * specific to PDF files.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = PdfFileExtension.class)
@JsonDeserialize(builder = PdfFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "version",
    "is_optimized",
    "document_info_dict",
    "pdfid0",
    "pdfid1" })
@JsonTypeName("pdf-ext")
//@AllowedParents({FileCoo.class})
//@TODO review creating a simple method that will do a "at least 1 field is used" so it can be used within the annotation rather than managing duplicate of all field names:
//@BusinessRule(ifExp = "true", thenExp = "getVersion().isPresent() == true || isOptimized().isPresent() == true || getDocumentInfoDict().isEmpty() == false || getPdfId0().isPresent() == true || getPdfId1().isPresent() == true", errorMessage = "At least 1 field must be used in PDF Extension.")
@SuppressWarnings("unused")
@Introspected

public interface PdfFileExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends PdfFileExtensionImpl.Builder {
    }

    static PdfFileExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static PdfFileExtension createPdfFileExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default PdfFileExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder().from(this)).build(); }

    @JsonProperty("version")
    Optional<String> getVersion();

    @JsonProperty("is_optimized")
    @NotNull
    Optional<Boolean> isOptimized();

    @JsonProperty("document_info_dict")
    Map<String,String> getDocumentInfoDict();

    @JsonProperty("pdfid0")
    Optional<String> getPdfId0();

    @JsonProperty("pdfid1")
    Optional<String> getPdfId1();

}
