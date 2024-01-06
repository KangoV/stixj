package io.kangov.stix.v21.custom;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kangov.stix.util.ImmutableStyle;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.function.UnaryOperator;


@Value.Immutable
@Serial.Version(1L)
//@DefaultTypeValue(value = "", groups = {DefaultValuesProcessor.class})
@ImmutableStyle
@JsonSerialize(as = GenericCustomObject.class)
@JsonDeserialize(builder = GenericCustomObject.Builder.class)
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
    "granular_markings"})
@SuppressWarnings("unused")

public interface GenericCustomObject extends CustomObject {

    /**
     * Exposes the generated builder outside this package
     * <p>
     * While the generated implementation (and consequently its builder) is not
     * visible outside this package, this builder inherits and exposes all public
     * methods defined on the generated implementation's Builder class.
     */
    class Builder extends GenericCustomObjectImpl.Builder {
    }

    static GenericCustomObject create(UnaryOperator<Builder> spec) { return spec.apply(builder()).build(); }
    static GenericCustomObject createBundle(UnaryOperator<Builder> spec) { return create(spec); }
    static Builder builder(UnaryOperator<Builder> spec) { return spec.apply(builder()); }
    static Builder builder() { return new Builder(); }

    default GenericCustomObject update(UnaryOperator<Builder> builder) {
        return builder.apply(builder().from(this)).build();
    }

}
