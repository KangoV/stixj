package io.kangov.stix.v21.core.sro;


import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;

public interface SroObject
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
        ExternalReferences {
}
