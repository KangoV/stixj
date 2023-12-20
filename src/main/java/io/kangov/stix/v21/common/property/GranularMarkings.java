package io.kangov.stix.v21.common.property;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.meta.mdo.GranularMarking;
import jakarta.annotation.Nonnull;

import java.util.Set;

public interface GranularMarkings {

    @Nonnull
    @JsonProperty("granular_markings")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Redactable
    Set<GranularMarking> getGranularMarkings();

}
