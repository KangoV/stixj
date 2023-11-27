package io.kangov.stix.common.type;

import io.kangov.stix.core.sdo.objects.Identity;

public record Identityreference(String id, Identity identity) {
    public boolean hasIdentity() {
        return identity != null;
    }
}
