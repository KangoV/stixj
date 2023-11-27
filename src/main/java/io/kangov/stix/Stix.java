package io.kangov.stix;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.runtime.Micronaut;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.serde.config.naming.PropertyNamingStrategy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Stix {

    private static final Logger log = LoggerFactory.getLogger(Stix.class);

    private static class LazyHolder {
        static final ApplicationContext INSTANCE = Micronaut.run(Stix.class);
    }

    public static Stix get() {
        return LazyHolder.INSTANCE.getBean(Stix.class);
    }

    private final ApplicationContext context;

    @Inject
    public Stix(ApplicationContext context) {
        this.context = context;
    }

    public Parser parser() {
        return context.getBean(Parser.class);
    }

}
