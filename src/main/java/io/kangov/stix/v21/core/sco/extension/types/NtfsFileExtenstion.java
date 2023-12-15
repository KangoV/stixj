package io.kangov.stix.v21.core.sco.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.extension.CyberObservableExtension;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The NTFS file extension specifies a default extension for capturing properties specific to the storage of the file on the NTFS file system.
 *
 */
@Value.Immutable @Serial.Version(1L)
@ImmutableStyle
@JsonSerialize(as = NtfsFileExtenstion.class)
@JsonDeserialize(builder = NtfsFileExtenstion.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({
    "sid",
    "alternate_data_streams" })
@JsonTypeName("ntfs-ext")
//@AllowedParents({FileCoo.class})
//@BusinessRule(ifExp = "true", thenExp = "getSid().isPresent() == true || getAlternateDataStreams().isEmpty() == false", errorMessage = "NTFS File Extension MUST contain at least one property from this extension")
@SuppressWarnings("unused")
@Introspected

public interface NtfsFileExtenstion extends CyberObservableExtension {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends NtfsFileExtenstionImpl.Builder {
    }

    static NtfsFileExtenstion create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static NtfsFileExtenstion createNtfsFileExtenstion(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default NtfsFileExtenstion update(UnaryOperator<Builder> builder) { return builder.apply(builder()).build(); }

    @JsonProperty("sid")
    Optional<String> getSid();

    // TODO:
//    @JsonProperty("alternate_data_streams")
//    @JsonPropertyDescription("Specifies a list of NTFS alternate data streams that exist for the file.")
//    Set<NtfsAlternateDataStreamObj> getAlternateDataStreams();

}
