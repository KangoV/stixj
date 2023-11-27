package io.kangov.stix.bundle;

import com.fasterxml.jackson.annotation.*;
import io.kangov.stix.common.type.RootBuilder;

import java.time.Instant;
import java.util.*;

@SuppressWarnings({"unused", "unchecked", "OptionalUsedAsFieldOrParameterType"})
public class BundleableBuilder<B> extends RootBuilder<B> {

    protected String id;
    protected String type;
    protected Map<String, Object> customProperties = new HashMap<>();

    public B id(String v) { this.id = v; return b(); }
    public B type(String v) { this.type = v; return b(); }
    public B customProperties(Map<String, Object> entries) { return properties(this.customProperties, entries); }
    public B putCustomProperty(String k, Object v) { return putProperty(customProperties, k, v);  }
    public B putCustomProperty(Map.Entry<String, Object> entry) { return putProperty(customProperties, entry); }
    public B putAllCustomProperties(Map<String, Object> entries) { return putAllProperties(this.customProperties, entries); }

    protected B preProcess(String type) {
        if (this.type == null) type("identity");
        if (this.id == null) id(this.type +"--"+UUID.randomUUID());
        return b();
    }


}
