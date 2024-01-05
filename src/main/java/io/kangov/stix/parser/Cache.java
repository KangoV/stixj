package io.kangov.stix.parser;

import io.kangov.stix.v21.bundle.Bundleable;
import jakarta.annotation.Nonnull;

import java.util.*;

public class Cache implements Iterable<Cache.Entry> {

    public record Entry(String id, Bundleable object) {
        public static Entry create(Bundleable bundleable) {
            return new Entry(bundleable.getId(), bundleable);
        }
    }

    private final Map<String, Entry> entries = new HashMap<>();

    @Nonnull
    @Override
    public Iterator<Entry> iterator() {
        return entries.values().iterator();
    }

    public boolean contains(String id) {
        return entries.containsKey(id);
    }

    public Optional<Entry> findEntry(String id) {
        return Optional.ofNullable(entries.get(id));
    }

    public Optional<Bundleable> findObject(String id) {
        return findEntry(id).map(Entry::object);
    }

    void put(Entry entry) {
        entries.put(entry.id(), entry);
    }
}
