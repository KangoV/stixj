package io.kangov.stix;

import io.kangov.stix.parser.Parser;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
