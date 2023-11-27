package io.kangov.stix.core.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.common.type.ExternalReference;
import io.kangov.stix.core.sdo.SdoObject;
import io.kangov.stix.validation.constraints.StartsWith;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.serde.config.naming.SnakeCaseStrategy;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

@SuppressWarnings({"unused"})
@Serdeable(naming = SnakeCaseStrategy.class)
//@JsonTypeName("identity")
public record Identity(

    // --- bundleable
    @NotNull String id,
    @NotNull String type,
    // --- SDO object
    @NotNull String specVersion,
    Optional<String> createdByRef,
    Instant created,
    Instant modified,
    Optional<Boolean> revoked,
    @NotNull Set<String> labels,
    Optional<Integer> confidence,
    Optional<String> lang,
    // --- this
    String name,
    Optional<String> description,
    Set<String> roles,
    Optional<String> identityClass,
    Set<String> sectors,
    Optional<String> contactInformation,
    Set<ExternalReference> externalReferences,
    @JsonAnySetter @JsonAnyGetter Map<@StartsWith("x_") String,Object> customProperties

) implements SdoObject {

    static Identity create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static Identity createTestObj(UnaryOperator<Builder> spec) { return create(spec); }

    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    @SuppressWarnings({"unused"})
    public static class Builder extends SdoBuilder<Builder> {

        String name;
        String description;
        Set<String> roles = new HashSet<>();
        String identityClass;
        Set<String> sectors = new HashSet<>();
        String contactInformation;

        public Builder name(String v) { this.name = v; return this; }
        public Builder description(String v) { this.description = v; return this; }
        public Builder roles(Iterable<String> elems) { return addAllElements(roles, elems); }
        public Builder roles(String ... elems) { return elements(roles, elems); }
        public Builder addRoles(String elem) { return addElement(roles, elem); }
        public Builder addRoles(String... elems) { return addElements(roles, elems); }
        public Builder addAllRoles(Iterable<String> elems) { return addAllElements(roles, elems); }
        public Builder identityClass(String v) { this.identityClass = v; return this; }
        public Builder sectors(Iterable<String> elems) { return addAllElements(sectors, elems); }
        public Builder sectors(String ... elems) { return elements(sectors, elems); }
        public Builder addSectors(String elem) { return addElement(sectors, elem); }
        public Builder addSectors(String... elems) { return addElements(sectors, elems); }
        public Builder addAllSectors(Iterable<String> elems) { return addAllElements(sectors, elems); }
        public Builder contactInformation(String v) { this.contactInformation = v; return this; }

        public Identity build() {
            preProcess("identity");
            return new Identity(
                // --- bundleable
                id,
                type,
                // --- SDO object
                specVersion,
                opt(createdByRef),
                created,
                modified,
                opt(revoked),
                labels,
                opt(confidence),
                opt(lang),
                // --- this
                name,
                opt(description),
                roles,
                opt(identityClass),
                sectors,
                opt(contactInformation),
                externalReferences,
                customProperties
            );
        }

    }

}
