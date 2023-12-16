package io.kangov.stix.v21.core.sco;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.core.Relateable;

public interface ScoObject
        extends
            Bundleable,
            Relateable,
            SpecVersionOptional,
            Defanged,
            Extensions {

}
