package io.kangov.stix.v21.common.property;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kangov.stix.redaction.Redactable;
import io.kangov.stix.v21.common.type.ObjectRef;
import io.kangov.stix.v21.meta.mdo.MarkingDefinition;
import jakarta.annotation.Nonnull;

import java.util.Set;

public interface ObjectMarkingRefs {

    String OBJECT_MARKING_REFS = "object_marking_refs";

    @JsonProperty(ObjectMarkingRefs.OBJECT_MARKING_REFS)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Redactable
    Set<ObjectRef<MarkingDefinition>> getObjectMarkingRefs();

}
