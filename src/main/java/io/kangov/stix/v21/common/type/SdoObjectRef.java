package io.kangov.stix.v21.common.type;

import io.kangov.stix.v21.core.sdo.SdoObject;

public record SdoObjectRef(String id, SdoObject sdoObject) {}
