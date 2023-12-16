package io.kangov.stix.config;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.core.sco.objects.*;
import io.kangov.stix.v21.core.sdo.objects.*;
import io.kangov.stix.v21.core.sro.objects.Relationship;
import io.kangov.stix.v21.core.sro.objects.Sighting;
import io.kangov.stix.v21.meta.mdo.MarkingDefinition;
import io.kangov.stix.v21.meta.mdo.objects.Statement;
import io.kangov.stix.v21.meta.mdo.objects.Tlp;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import java.lang.Process;

@Factory
public class Factories {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .registerModule(generateStixSubTypesModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .enable(SerializationFeature.INDENT_OUTPUT)
            ;
    }

    private static SimpleModule generateStixSubTypesModule() {

        SimpleModule module = new SimpleModule();

        Class<?>[] sdoClasses = {
            AttackPattern.class,
            Campaign.class,
            CourseOfAction.class,
            Grouping.class,
            Identity.class,
            Indicator.class,
            Infrastructure.class,
            IntrusionSet.class,
            Location.class,
            Malware.class,
            MalwareAnalysis.class,
            ObservedData.class,
            Report.class,
            ThreatActor.class,
            Tool.class,
            Vulnerability.class
        };

        Class<?>[] sroClasses = {
            Relationship.class,
            Sighting.class
        };

        Class<?>[] dataMarkingClasses = {
            MarkingDefinition.class,
            Statement.class,
            Tlp.class
        };

        Class<?>[] bundleClasses = {
            Bundle.class
        };

        Class<?>[] cyberObservableClasses = {
            Artifact.class,
            AutonomousSystem.class,
            Directory.class,
            DomainName.class,
            EmailAddress.class,
            EmailMessage.class,
            File.class,
            Ipv4Address.class,
            Ipv6Address.class,
            MacAddress.class,
            Mutex.class,
            NetworkTraffic.class,
            Process.class,
            Software.class,
            Url.class,
            UserAccount.class,
            WindowsRegistryKey.class,
            X509Certificate.class,
            // TODO: Load these in from application.yml
            XNominetBlock.class,
            XNominetThreatFeedSource.class
        };

        Class<?>[] cyberObservableExtensionClasses = {
//            ArchiveFileExtension.class,
//            HttpRequestExtension.class,
//            IcmpExtension.class,
//            NetworkSocketExtension.class,
//            NtfsFileExtenstion.class,
//            PdfFileExtension.class,
//            RasterImageFileExtension.class,
//            TcpExtension.class,
//            UnixAccountExtension.class,
//            WindowsPeBinaryFileExtension.class,
//            WindowsProcessExtension.class,
//            WindowsServiceExtension.class
        };

        return module
            .registerSubtypes(sdoClasses)
            .registerSubtypes(sroClasses)
            .registerSubtypes(dataMarkingClasses)
            .registerSubtypes(bundleClasses)
            .registerSubtypes(cyberObservableClasses)
            .registerSubtypes(cyberObservableExtensionClasses);

    }
}
