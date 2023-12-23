package io.kangov.stix.v21.core.sdo;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.core.Relateable;

/**
 * STIX Domain Objects
 * <p>
 * Higher Level Intelligence Objects that represent behaviors and constructs that threat analysts would typically
 * create or work with while understanding the threat landscape.
 * <p>
 * STIX defines a set of STIX Domain Objects (SDOs): Attack Pattern, Campaign, Course of Action, Grouping, Identity,
 * Indicator, Infrastructure, Intrusion Set, Location, Malware, Malware Analysis, Note, Observed Data, Opinion, Report,
 * Threat Actor, Tool, and Vulnerability. Each of these objects corresponds to a concept commonly used in CTI.
 */
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

}
