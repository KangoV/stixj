package io.kangov.stix.common.type;

import java.time.Instant;
import java.util.*;

@SuppressWarnings({"unused", "unchecked", "OptionalUsedAsFieldOrParameterType"})
public class RootBuilder<B>  {

    protected B b() {
        return (B) this;
    }

    // Utils

    protected Instant now() {
        return Instant.now();
    }

    protected <T> Optional<T> opt(T value) {
        return Optional.ofNullable(value);
    }

    protected <T> T get(Optional<T> opt) {
        return opt.orElse(null);
    }

    protected <T> B elements(Collection<T> elements, Iterable<T> elementsToReplace) {
        elements.clear();
        addAllElements(elements, elementsToReplace);
        return b();
    }

    protected <T> B elements(Collection<T> elements, T ... elementsToReplace) {
        elements.clear();
        addElements(elements, elementsToReplace);
        return b();
    }

    protected <T> B addAllElements(Collection<T> elements, Iterable<T> elementsToAdd) {
        for (T element : elementsToAdd) {
            elements.add(Objects.requireNonNull(element, "labels element"));
        }
        return b();
    }

    protected <T> B addElement(Collection<T> elements, T element) {
        elements.add(Objects.requireNonNull(element, "labels element"));
        return b();
    }

    protected <T> B addElements(Collection<T> elements, T... toAdd) {
        for (T element : toAdd) {
            elements.add(Objects.requireNonNull(element, "labels element"));
        }
        return b();
    }

    protected <V> B putProperty(Map<String, V> map, String key, V value) {
        map.put(key,value);
        return b();
    }

    protected <V> B putProperty(Map<String, V> map, Map.Entry<String, V> entry) {
        map.put(entry.getKey(), entry.getValue());
        return b();
    }


    protected <V> B putAllProperties(Map<String, V> map, Map<String, V> entries) {
        map.putAll(entries);
        return b();
    }
    protected <V> B properties(Map<String, V> map, Map<String, V> entries) {
        map.clear();
        return putAllProperties(map, entries);
    }

}
