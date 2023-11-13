package io.kangov.stixj.common.objects.sdo;

import com.fasterxml.jackson.annotation.*;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stixj.ImmutableStyle;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;


/**
 * identity
 * <p>
 * Identities can represent actual individuals, organizations, or groups (e.g., ACME, Inc.) as well as classes of individuals, organizations, or groups.
 * 
 */
@Value.Immutable
@Serial.Version(1L)
@ImmutableStyle
@JsonTypeName("identity")
@JsonSerialize(as = Identity.class)
@JsonDeserialize(builder = Identity.Builder.class)
@JsonPropertyOrder({
    "type",
    "id",
    "created_by_ref",
    "created",
    "modified",
    "revoked",
    "labels",
    "external_references",
    "object_marking_refs",
    "granular_markings",
    "name",
    "description",
    "identity_class",
    "sectors",
    "contact_information"})

public interface IdentitySpec extends Sdo {

    // Note for the labels attribute:
    // The list of roles that this Identity performs (e.g., CEO, Domain Administrators, Doctors, Hospital, or Retailer). No open vocabulary is yet defined for this property.

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @JsonProperty("roles")
    Set<String> getRoles();

//    @JsonProperty("identity_class")
//    Optional<@Vocab(IdentityClasses.class) String> getIdentityClass();
//
//    @Vocab(IndustrySectors.class)
//    @JsonProperty("sectors")
//    @Redactable
//    Set<String> getSectors();
//
//    @JsonProperty("contact_information")
//    @Redactable
//    Optional<String> getContactInformation();

}
