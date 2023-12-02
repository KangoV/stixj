package io.kangov.stix.v21.core.sdo;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;

public interface SdoObject
        extends
            Bundleable,
            SpecVersion,
    CreatedByRef,
    Created,
            Modified,
            Revoked,
            Labels,
    Confidence,
            Lang,
            ExternalReferences,
            StixCustomProperties {

    /**
     * This is used with the SROs.  The SRO interface enforces what relationships can be created.  The Relationships can then be stored in the Domain object if they choose.
     * Otherwise you would typically add these Relationship SROs that are specific to SDOs, can be grabbed during bundle creation.
     * @return Set of Relationship SROs
     */
//    @JsonIgnore
//    Set<RelationshipSro> getRelationships();

}
