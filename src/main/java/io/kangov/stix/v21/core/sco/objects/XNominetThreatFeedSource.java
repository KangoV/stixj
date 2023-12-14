package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.micronaut.core.annotation.Introspected;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.*;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;

/**
 * directory
 * <p>
 * The Directory Object represents the properties common to a file system directory.
 * 
 */

@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue( value = "directory", groups = { DefaultValuesProcessor.class })
@ImmutableStyle
@JsonTypeName("x-nominet-threat-feed-source")
@JsonSerialize(as = XNominetThreatFeedSource.class)
@JsonDeserialize(builder = XNominetThreatFeedSource.Builder.class)
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "name",
    "meta_data" })
@JsonInclude( value = NON_EMPTY, content= NON_EMPTY )
@SuppressWarnings("unused")
@Introspected

public interface XNominetThreatFeedSource extends ScoObject {

    record MetadataEntry(String key, String value) {}

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends XNominetThreatFeedSourceImpl.Builder {
    }

    static XNominetThreatFeedSource create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static XNominetThreatFeedSource createXNominetBlock(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default XNominetThreatFeedSource update(UnaryOperator<Builder> builder) {
        return builder.apply(builder()).build();
    }

    @JsonProperty("name")
    String getName();

    // TODO: Will need a deser to get it into the wacky format that we need:
    // {
    //   "key": "threat_type"
    //   "value": "threat"
    // }
    // why the didn't just do "threat_type": "threat" is beyond me!
    @JsonProperty("meta_data")
    List<MetadataEntry> getMetadata();


}
