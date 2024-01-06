package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.ScoExtension;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Raster Image file extension specifies a default extension for capturing
 * properties specific to image files.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = RasterImageFileExtension.class)
@JsonDeserialize(builder = RasterImageFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "image_height", "image_width", "bits_per_pixel", "image_compression_algorithm", "exif_tags" })
@JsonTypeName("raster-image-ext")
//@AllowedParents({FileCoo.class})
//@BusinessRule(ifExp = "true", thenExp = "getImageHeight().isPresent() == true || getImageWidth().isPresent() == true || getBitsPerPixel().isPresent() == false || getImageCompressionAlgorithm().isPresent() == true || getExifTags().isEmpty() == true", errorMessage = "At least 1 field must be used in Raster Image File Extension.")
@SuppressWarnings("unused")
@Introspected

public interface RasterImageFileExtension extends ScoExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends RasterImageFileExtensionImpl.Builder {
    }

    static RasterImageFileExtension create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static RasterImageFileExtension createRasterImageFileExtension(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default RasterImageFileExtension update(UnaryOperator<Builder> builder) { return builder.apply(builder().from(this)).build(); }

    //@TODO Spec is missing direction about limits: Value likely needs to be MUST be positive
    @JsonProperty("image_height")
    Optional<Long> getImageHeight();

    //@TODO Spec is missing direction about limits: Value likely needs to be MUST be positive
    @JsonProperty("image_width")
    Optional<Long> getImageWidth();

    @JsonProperty("bits_per_pixel")
    Optional<Long> getBitsPerPixel();

    @JsonProperty("image_compression_algorithm")
    Optional<String> getImageCompressionAlgorithm();

    @JsonProperty("exif_tags")
    Map<String,Object> getExifTags();

}
