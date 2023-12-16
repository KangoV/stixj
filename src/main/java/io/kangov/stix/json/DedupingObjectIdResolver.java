package io.kangov.stix.json;

import com.fasterxml.jackson.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class DedupingObjectIdResolver extends SimpleObjectIdResolver {

    private static final Logger log = LoggerFactory.getLogger(DedupingObjectIdResolver.class);

    public void bindItem(ObjectIdGenerator.IdKey id, Object ob) {
        log.debug("bind_item: key=[{}], obj=[{}]", id, ob);
        if (_items == null) {
            _items = new HashMap<>();
        }
        _items.put(id, ob);
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return new DedupingObjectIdResolver();
    }
}