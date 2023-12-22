package io.kangov.stix.v21.custom;

import io.kangov.stix.v21.bundle.Bundleable;
import io.kangov.stix.v21.common.property.*;
import io.kangov.stix.v21.core.Relateable;
import io.kangov.stix.validation.constraints.StartsWith;
import io.micronaut.core.annotation.Introspected;

@Introspected
public interface CustomObject extends
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
    Defanged,
    ExternalReferences {

    String SPEC_VERSION = "spec_version";
    String CREATED_BY_REF = "created_by_ref";
    String CREATED = "created";
    String MODIFIED = "modified";
    String REVOKED = "revoked";
    String LABELS = "labels";
    String CONFIDENCE = "confidence";
    String LANG = "lang";
    String DEFANGED = "defanged";
    String EXTERNAL_REFERENCE = "external_references";

    @Override
    @StartsWith("x-")
    String getType();

    @Override
    @StartsWith("x-")
    String getId();

}
