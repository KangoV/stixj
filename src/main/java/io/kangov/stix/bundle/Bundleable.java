package io.kangov.stix.bundle;


import java.util.HashMap;
import java.util.Map;

public interface Bundleable {

    String id();
    String type();
    Map<String, Object> customProperties();
}
