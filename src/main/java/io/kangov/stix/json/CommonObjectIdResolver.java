package io.kangov.stix.json;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class CommonObjectIdResolver implements ObjectIdResolver {

    private static class LazyHolder {
        private static final ObjectIdResolver INSTANCE = new CommonObjectIdResolver();
    }

    private static final Logger log = LoggerFactory.getLogger(CommonObjectIdResolver.class);

    protected final Map<IdKey, WeakReference<Object>> _items = new HashMap<>();

    @Override
    public void bindItem(IdKey id, Object pojo) {
        log.debug("binding item: {} to: {}", id, pojo);
        _items.put(id, new WeakReference<>(pojo));
    }

    @Override
    public Object resolveId(IdKey id) {
        log.debug("resolving id: {}", id);
        Object result = null;
        var ref = _items.get(id);
        if (ref != null) {
            var obj = ref.get();
            if (obj == null) {
                log.debug("weak reference is null so unbinding {}", id);
                _items.remove(id);
            } else {
                log.debug("resolved: {}", obj);
                result = obj;
            }
        } else {
            log.debug("resolve failed, id not bound: {}", id);
        }
        return result;
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return LazyHolder.INSTANCE;
    }

    @Override
    public boolean canUseFor(ObjectIdResolver resolverType) {
        return resolverType.getClass() == getClass();
    }
}