package io.kangov.stix.v21.core.sco.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import io.kangov.stix.v21.core.sco.ScoObject;
import io.kangov.stix.validation.constraints.Vocab;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static io.kangov.stix.v21.bundle.Bundleable.*;
import static io.kangov.stix.v21.enums.Vocabs.Vocab.ACCOUNT_TYPE;

/**
 * user-account
 * <p>
 * The User Account Object represents an instance of any type of user account,
 * including but not limited to operating system, device, messaging service, and
 * social media platform accounts.
 *
 */
@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "user-account", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = UserAccount.class)
@JsonDeserialize(builder = UserAccount.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("user-account")
@JsonPropertyOrder({
    TYPE,
    SPEC_VERSION,
    ID,
    "extensions",
    "user_id",
    "account_login",
    "account_type",
    "display_name",
    "is_service_account",
    "is_privileged",
    "can_escalate_privs",
    "is_disabled",
    "account_created",
    "account_expires",
    "password_last_changed",
    "account_first_login",
    "account_last_login" })
@SuppressWarnings("unused")
@Introspected

public interface UserAccount extends ScoObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends UserAccountImpl.Builder {
    }

    static UserAccount create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static UserAccount createUserAccount(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default UserAccount update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

    @JsonProperty("user_id")
    @NotBlank
    String getUserId();

    @JsonProperty("account_login")
    Optional<String> getAccountLogin();

    @JsonProperty("account_type")
    Optional<@Vocab(ACCOUNT_TYPE) String> getAccountType();

    @JsonProperty("display_name")
    Optional<String> getDisplayName();

    @JsonProperty("is_service_account")
    @NotNull
    Optional<Boolean> isServiceAccount();

    @JsonProperty("is_privileged")
    @NotNull
    Optional<Boolean> isPrivileged();

    @JsonProperty("can_escalate_privs")
    @NotNull
    Optional<Boolean> isCanEscalatePrivs();

    @JsonProperty("is_disabled")
    @NotNull
    Optional<Boolean> isDisabled();

    @JsonProperty("account_created")
    Optional<Instant> getAccountCreated();

    @JsonProperty("account_expires")
    Optional<Instant> getAccountExpires();

    @JsonProperty("password_last_changed")
    Optional<Instant> getPasswordLastChanged();

    @JsonProperty("account_first_login")
    Optional<Instant> getAccountFirstLogin();

    @JsonProperty("account_last_login")
    Optional<Instant> getAccountLastLogin();

}
