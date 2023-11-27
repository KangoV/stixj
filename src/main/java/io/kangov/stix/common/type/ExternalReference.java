package io.kangov.stix.common.type;


import io.kangov.stix.core.sdo.objects.Identity;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.serde.config.naming.SnakeCaseStrategy;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * External references are used to describe pointers to information represented outside STIX. For example, a Malware
 * object could use an external reference to indicate an ID for that malware in an external database or a report could
 * use references to represent source material.
 */

@Serdeable(naming = SnakeCaseStrategy.class)
public record ExternalReference(

    String sourceName,
    Optional<String> description,
    Optional<String> url,
    Map<String, String> hashes,
    Optional<String> externalId

) {

    public static ExternalReference create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    public static ExternalReference createExternalReference(UnaryOperator<Builder> spec) { return create(spec); }

    public static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    public static Builder builder() { return new Builder(); }

    @SuppressWarnings({"unused"})
    public static class Builder extends RootBuilder<Builder> {

        String sourceName;
        String description;
        String url;
        Map<String, String> hashes = new HashMap<>();
        String externalId;

        public Builder sourceName(String v) { sourceName = v; return this; }
        public Builder description(String v) { description = v; return this; }
        public Builder url(String v) { url = v; return this; }
        public Builder hashes(Map<String, String> entries) { return properties(hashes, entries); }
        public Builder putHashes(String k, String v) { return putProperty(hashes, k, v);  }
        public Builder putHash(Map.Entry<String, String> entry) { return putProperty(hashes, entry); }
        public Builder putAllHashes(Map<String, String> entries) { return putAllProperties(this.hashes, entries); }
        public Builder externalId(String v) { externalId = v; return this; }

        public ExternalReference build() {
            return new ExternalReference(
                sourceName,
                opt(description),
                opt(url),
                hashes,
                opt(externalId)
            );
        }

    }

}
