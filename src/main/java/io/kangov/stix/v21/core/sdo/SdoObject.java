package io.kangov.stix.v21.core.sdo;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.core.Relateable;

public interface SdoObject
        extends
            Bundleable,
            Relateable,
            SpecVersion,
            CreatedByRef,
            Created,
            Modified,
            Revoked,
            Labels,
            Confidence,
            Lang,
            ExternalReferences {

    String CREATED_BY_REF = "created_by_ref";
    String CREATED = "created";
    String MODIFIED = "modified";
    String REVOKED = "revoked";
    String LABELS = "labels";
    String CONFIDENCE = "confidence";
    String LANG = "lang";
    String EXTERNAL_REFERENCE = "external_references";
    String OBJECT_MARKING_REFS = "object_marking_refs";
    String GRANULAR_MARKINGS = "granular_markings";

    /**
     * This is used with the SROs.  The SRO interface enforces what relationships can be created.  The Relationships can then be stored in the Domain object if they choose.
     * Otherwise you would typically add these Relationship SROs that are specific to SDOs, can be grabbed during bundle creation.
     * @return Set of Relationship SROs
     */
//    @JsonIgnore
//    Set<RelationshipSro> getRelationships();

}
