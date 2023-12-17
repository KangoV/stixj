package io.kangov.stix.v21.core.sco;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.core.Relateable;

/**
 * STIX Cyber-observable Objects
 * <p>
 * Objects that represent observed facts about a network or host that may be used and related to higher level
 * intelligence to form a more complete understanding of the threat landscape.
 * <p>
 * STIX defines a set of STIX Domain Objects (SDOs): Attack Pattern, Campaign, Course of Action, Grouping, Identity,
 * Indicator, Infrastructure, Intrusion Set, Location, Malware, Malware Analysis, Note, Observed Data, Opinion, Report,
 * Threat Actor, Tool, and Vulnerability.
 * <p>
 * Each of these objects corresponds to a concept commonly used in CTI.
 */
public interface ScoObject
        extends
            Bundleable,
            Relateable,
            SpecVersionOptional,
            Defanged,
            Extensions {

}
