package io.kangov.stix.v21.meta.mdo;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.core.Relateable;

public interface MdoObject
        extends
            Bundleable,
            Relateable,
            SpecVersion,
            CreatedByRef,
            Created,
            ExternalReferences,
            // TODO: object marking refs
            // TODO: granular markings
            Extensions {

    String CREATED_BY_REF     = "created_by_ref";
    String CREATED            = "created";
    String EXTERNAL_REFERENCE = "external_references";

}
