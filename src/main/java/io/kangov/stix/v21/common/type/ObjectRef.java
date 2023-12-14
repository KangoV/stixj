package io.kangov.stix.v21.common.type;

import io.kangov.stix.v21.bundle.Bundleable;

public sealed interface ObjectRef<T extends Bundleable>
        permits
            BundleableRef,
            ScoObjectRef,
            SdoObjectRef,
            IdentityRef {

    String id();

    T object();

}
