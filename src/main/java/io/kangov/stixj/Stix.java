package io.kangov.stixj;

//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
//import io.kangov.stixj.common.bundle.Bundle;
//import io.kangov.stixj.common.objects.sco.DomainName;
//import io.kangov.stixj.common.objects.sdo.Identity;
import io.micronaut.context.ApplicationContext;
//import io.micronaut.context.BeanContext;
//import io.micronaut.context.annotation.Bean;
//import io.micronaut.context.annotation.Factory;
import io.micronaut.runtime.Micronaut;
import io.micronaut.serde.ObjectMapper;
//import io.micronaut.serde.config.naming.PropertyNamingStrategy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stix {

    private static final Logger log = LoggerFactory.getLogger(Stix.class);

    private static class LazyHolder {
        static final Stix INSTANCE = Micronaut.run(Stix.class).getBean(Stix.class);
    }

    @Singleton
    public class Parser {
        private final ObjectMapper objectMapper;
        @Inject
        Parser(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        // TODO: implement parsing methods
    }

//    @Factory
//    static class Factories {
//        private static final Logger log = LoggerFactory.getLogger(Factories.class);
//        @Bean
//        ObjectMapper createMapper(BeanContext ctx) {
//            var om = ctx.getBean(ObjectMapper.class);
//            om.
//            var o =  new ObjectMapper()
//                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
//                .registerModule(new ParameterNamesModule())
//                .registerModule(new Jdk8Module())
//                .registerModule(new JavaTimeModule())
//                .registerModule(generateStixSubTypesModule())
//                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//            log.info("STIX ObjectMapper configured");
//            return o;
//        }
//    }

    public static Stix get() {
        return LazyHolder.INSTANCE;
    }

    private final ApplicationContext context;

    @Inject
    public Stix(ApplicationContext context) {
        this.context = context;
    }

    public ObjectMapper objectMapper() {
        return context.getBean(ObjectMapper.class);
    }

    public Parser parser() {
        return context.getBean(Parser.class);
    }

//    private static SimpleModule generateStixSubTypesModule() {
//
//        SimpleModule module = new SimpleModule();
//
//        Class<?>[] sdoClasses = {
//            Identity.class
//        };
//
//        Class<?>[] sroClasses = {
//        };
//
//        Class<?>[] dataMarkingClasses = {
//        };
//
//        Class<?>[] bundleClasses = {
//            Bundle.class
//        };
//
//        Class<?>[] scoClasses = {
//            DomainName.class
//        };
//
//        Class<?>[] cyberObservableExtensionClasses = {
//        };
//
//        return module
//            .registerSubtypes(sdoClasses)
//            .registerSubtypes(scoClasses)
//            .registerSubtypes(sroClasses)
//            .registerSubtypes(dataMarkingClasses)
//            .registerSubtypes(bundleClasses)
//            .registerSubtypes(cyberObservableExtensionClasses);
//
//    }

}
