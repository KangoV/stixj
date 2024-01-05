package io.kangov.stix.util.mock;

import io.kangov.stix.v21.common.type.ExternalReference;
import io.kangov.stix.v21.core.sdo.objects.*;
import io.kangov.stix.v21.core.sdo.types.KillChainPhase;
import io.kangov.stix.v21.enums.Vocabs;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import net.andreinc.mockneat.MockNeat;

import java.util.*;

import static io.kangov.stix.v21.enums.Vocabs.Vocab.*;

@Singleton
public class Mocks {

    private static final Randoms random = new Randoms(MockNeat.threadLocal());
    private final Faker fake;

    @Inject
    Mocks(Faker fake) {
        this.fake = fake;
    }

    public AttackPattern mockAttackPattern() {

        var builder = AttackPattern.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific
        builder.name(random.spacedWords(5));
        fake.coinFlip(() -> fake.repeatUpTo(5, () -> builder.addKillChainPhase(mockKillChainPhase())));
        fake.coinFlip(() -> builder.description(random.spacedWords(5)));

        return builder.build();
    }

    public Campaign mockCampaign() {

        var builder = Campaign.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));


        // ## --- specific
        fake.coinFlip(() -> {
            var firstSeen = random.instant();
            builder.firstSeen(firstSeen);
            fake.coinFlip(() -> builder.lastSeen(random.instantAfter(firstSeen)));
        });
        builder.name(random.word());
        fake.coinFlip(() -> builder.description(random.spacedWords(5)));
        fake.coinFlip(() -> builder.objective(random.spacedWords(25)));
        fake.coinFlip(() -> fake.repeatBetween(1, 5,  () -> builder.addAlias(random.joinedWords(5, "-"))));

        return builder.build();
    }

    public CourseOfAction mockCourseOfAction() {

        var builder = CourseOfAction.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific
        builder.name(random.word());
        fake.coinFlip(() -> builder.description(random.spacedWords(5)));
        fake.coinFlip(() -> fake.repeatBetween(1, 5,  () -> builder.addAction(random.joinedWords(5, "-"))));

        return builder.build();
    }

    public Grouping mockGrouping() {

        var builder = Grouping.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific
        fake.coinFlip(() -> builder.name(random.joinedWords(5, "-")));
        fake.coinFlip(() -> builder.context(random.spacedWords(5)));
        fake.coinFlip(() -> builder.description(random.spacedWords(5)));
        fake.coinFlip(() -> builder.objectRefs(random.iterableOfWords(5)));

        return builder.build();
    }

    public Identity mockIdentity() {

        var builder = Identity.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific
        builder.name(random.joinedWords(5, "-"));
        fake.coinFlip(() -> builder.description(random.spacedWords(10)));
        fake.coinFlip(() -> builder.contactInformation(String.format("%s %s", random.email(), random.department())));
        fake.coinFlip(() -> builder.identityClass(random.stringFrom(IDENTITY_CLASS.entries())));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addRole(random.csvWords(5))));
        fake.coinFlip(() -> fake.repeatUpTo( 5, () -> builder.addSector(random.stringFrom(INDUSTRY_SECTORS.entries()))));

        return builder.build();
    }

    public Indicator mockIndicator() {

        var builder = Indicator.builder();

        // ## --- common

        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        fake.coinFlip(() -> {
            var from = random.instant();
            builder.validFrom(from);
            fake.coinFlip(() -> builder.validUntil(random.instantAfter(from)));
        });
        builder.pattern("SOME PATTERN GOES HERE");
        fake.coinFlip(() -> builder.name(random.joinedWords(5,"-")));
        fake.coinFlip(() -> builder.description(random.spacedWords(10)));
        fake.coinFlip(() -> fake.repeatBetween(0, 15, () -> builder.addKillChainPhase(mockKillChainPhase())));

        return builder.build();
    }

    public Infrastructure mockInfrastructure() {

        var builder = Infrastructure.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific
        builder.name(random.spacedWords(3));
        fake.coinFlip(() -> builder.description(random.spacedWords(5)));
        fake.coinFlip(() -> fake.repeatUpTo(3, () -> builder.addKillChainPhase(mockKillChainPhase())));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addInfrastructureType(randomVocabEntry(INFRASTRUCTURE_TYPE))));
        fake.coinFlip(() -> fake.repeatBetween(0, 5,  () -> builder.addAlias(random.kebabWords(5))));
        fake.coinFlip(() -> {
            var from = random.instant();
            builder.firstSeen(from);
            fake.coinFlip(() -> builder.lastSeen(random.instantAfter(from)));
        });

        return builder.build();
    }

    public static String randomVocabEntry(Vocabs.Vocab vocab) {
        return random.elementFrom(vocab.entries());
    }

    public IntrusionSet mockIntrusionSet() {

        var builder = IntrusionSet.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        fake.coinFlip(() -> {
            var from = random.instant();
            builder.firstSeen(from);
            fake.coinFlip(() -> builder.lastSeen(random.instantAfter(from)));
        });

        builder.name(random.joinedWords(5,"-"));
        fake.coinFlip(() -> builder.description(random.spacedWords(10)));
        fake.coinFlip(() -> builder.resourceLevel(random.elementFrom(ATTACK_RESOURCE_LEVEL.entries())));
        fake.coinFlip(() -> builder.primaryMotivation(random.elementFrom(ATTACK_MOTIVATION.entries())));
        fake.coinFlip(() -> fake.repeatBetween(0, 15, () -> builder.addGoal(random.spacedWords(10))));
        fake.coinFlip(() -> fake.repeatBetween(1, 5,  () -> builder.addSecondaryMotivation(random.elementFrom(ATTACK_MOTIVATION.entries()))));
        fake.coinFlip(() -> fake.repeatBetween(0, 5,  () -> builder.addAlias(random.kebabWords(5))));

        return builder.build();
    }

    public Location mockLocation() {

        var builder = Location.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        fake.coinFlip(() -> builder.name(random.kebabWords(5)));
        fake.coinFlip(() -> builder.description(random.spacedWords(10)));
        fake.coinFlip(() -> builder.latitude(random.doubleVal(-90.0, 90.0)));
        fake.coinFlip(() -> builder.longitude(random.doubleVal(-180.0, 180.0)));
        fake.coinFlip(() -> builder.precision(random.doubleVal()));
        fake.coinFlip(() -> builder.region(random.elementFrom(Vocabs.Vocab.REGION.entries())));
        fake.coinFlip(() -> builder.country(random.countryIso2()));
        fake.coinFlip(() -> builder.administrativeArea(random.word()));
        fake.coinFlip(() -> builder.city(random.city()));
        fake.coinFlip(() -> builder.streetAddress(random.spacedWords(5)));
        fake.coinFlip(() -> builder.postalCode(random.word()));

        var obj = builder.build();
        return obj;
    }

    public Malware mockMalware() {

        var builder = Malware.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        builder.isFamily(random.bool(5));
        fake.coinFlip(() -> builder.name(random.kebabWords(5)));
        fake.coinFlip(() -> builder.description(random.spacedWords(10)));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addMalwareType(random.elementFrom(MALWARE_TYPE.entries()))));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addAlias(random.word())));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addKillChainPhase(mockKillChainPhase())));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> {
            var first_seen = random.instant();
            builder.firstSeen(first_seen);
            builder.lastSeen(random.instantAfter(first_seen));
        }));
        // TODO: operating system refs
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addArchitectureExecutionEnv(random.elementFrom(PROCESSOR_ARCHITECTURE.entries()))));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addImplementationLanguage(random.elementFrom(IMPLEMENTATION_LANGUAGES.entries()))));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addCapabilities(random.elementFrom(MALWARE_CAPABILITIES.entries()))));
        // TODO: sample refs

        return builder.build();
    }

    public MalwareAnalysis mockMalwareAnalysis() {

        var builder = MalwareAnalysis.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        builder.product(random.kebabWords(5));
        fake.coinFlip(() -> builder.version(random.word()));
        fake.coinFlip(() -> builder.hostVMRef(random.word()));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addOperatingSystemRef(random.word())));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addInstalledSoftwareRef(random.word())));
        fake.coinFlip(() -> builder.configurationVersion(random.word()));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addModule(random.word())));
        fake.coinFlip(() -> builder.analysisEngineVersion(random.word()));
        fake.coinFlip(() -> builder.analysisDefinitionVersion(random.word()));
        fake.coinFlip(() -> builder.submitted(random.instant()));
        fake.coinFlip(() -> builder.analysisStarted(random.instant()));
        fake.coinFlip(() -> builder.analysisEnded(random.instant()));
        fake.coinFlip(() -> builder.resultName(random.word()));
        fake.coinFlip(() -> builder.result(random.elementFrom(MALWARE_RESULT)));
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> builder.addAnalysisScoRef(random.word())));
        fake.coinFlip(() -> builder.sampleRef(random.word()));

        return builder.build();

    }

    public Note mockNote() {

        var builder = Note.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific
        fake.coinFlip(() -> builder.summary(random.spacedWords(10)));
        fake.coinFlip(() -> builder.content(random.spacedWords(32)));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addAuthor(random.fullName())));
        // TODO: support object refs

        return builder.build();
    }

    public ObservedData mockObservedData() {

        var builder = ObservedData.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        builder.firstObserved(random.instant());
        builder.firstObserved(random.instant());
        builder.numberObserved(random.integer(20));

//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockAutonomousSystemCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockDirectoryCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockDomainNameCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockEmailAddressCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockEmailMessageCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockFileCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockIpv4AddressCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockIpv6AddressCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockMacAddress())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockMutexCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockNetworkTrafficCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockProcessCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockSoftwareCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockUrlCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockUserAccountCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockWindowsRegistryKeyCoo())));
//        fake.coinFlip(() -> fake.repeatBetween(1, 5, () -> builder.addObject(mockX509CertificateCoo())));

        return builder.build();
    }

    public Opinion mockOpinion() {

        var builder = Opinion.builder();

        // ## --- common
        var objectCreated = random.instant();
        fake.coinFlip(() -> {
            builder.created(objectCreated);
            fake.coinFlip(() -> builder.modified(random.instantAfter(objectCreated)));
        });
        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific
        fake.coinFlip(() -> builder.explanation(random.spacedWords(10)));
        fake.coinFlip(() -> builder.opinion(random.spacedWords(32)));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addAuthor(random.fullName())));
        // TODO: support object refs

        return builder.build();
    }

    public Report mockReport() {

        Report.Builder builder = Report.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        builder.name(random.joinedWords(1, 8, "-"));
        builder.published(random.instant());
        fake.coinFlip(() -> builder.description(random.spacedWords(10)));
        fake.repeatBetween(1, 50, () -> {
            switch (random.integer(1, 14)) {
                case 1  -> builder.addObjectRef(mockAttackPattern());
                case 2  -> builder.addObjectRef(mockCampaign());
                case 3  -> builder.addObjectRef(mockCourseOfAction());
                case 4  -> builder.addObjectRef(mockIdentity());
                case 5  -> builder.addObjectRef(mockIndicator());
                case 6  -> builder.addObjectRef(mockIntrusionSet());
                case 7  -> builder.addObjectRef(mockMalware());
                case 8  -> builder.addObjectRef(mockObservedData());
                case 9  -> builder.addObjectRef(mockThreatActor());
//                case 10 -> builder.addObjectRef(mockTool());
//                case 11 -> builder.addObjectRef(mockVulnerability());
//                case 12 -> builder.addObjectRef(mockMarkingDefinition());
//                case 13 -> builder.addObjectRef(mockRelationship());
//                case 14 -> builder.addObjectRef(mockSighting());
            }
        });

        return builder.build();
    }

    public ThreatActor mockThreatActor() {

        ThreatActor.Builder builder = ThreatActor.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        builder.name(random.name());
        fake.coinFlip(() -> fake.repeatBetween(0, 5, () -> {
            var first_seen = random.instant();
            builder.firstSeen(first_seen);
            builder.lastSeen(random.instantAfter(first_seen));
        }));
        fake.coinFlip(() -> builder.description(random.description()));
        fake.coinFlip(() -> builder.sophistication(random.elementFrom(THREAT_ACTOR_SOPHISTICATION.entries())));
        fake.coinFlip(() -> builder.resourceLevel(random.elementFrom(ATTACK_RESOURCE_LEVEL.entries())));
        fake.coinFlip(() -> builder.primaryMotivation(random.elementFrom(ATTACK_MOTIVATION.entries())));
        fake.coinFlip(() -> fake.repeatBetween(1,  5, () -> builder.addAlias(random.name())));
        fake.coinFlip(() -> fake.repeatBetween(1,  5, () -> builder.addRole(random.elementFrom(THREAT_ACTOR_ROLE))));
        fake.coinFlip(() -> fake.repeatBetween(1,  5, () -> builder.addGoal(random.spacedWords(10))));
        fake.coinFlip(() -> fake.repeatBetween(1,  5, () -> builder.addThreatActorType(random.elementFrom(THREAT_ACTOR_TYPE.entries()))));
        fake.coinFlip(() -> fake.repeatBetween(1,  5, () -> builder.addSecondaryMotivation(random.elementFrom(ATTACK_MOTIVATION))));
        fake.coinFlip(() -> fake.repeatBetween(1,  5, () -> builder.addPersonalMotivation(random.elementFrom(ATTACK_MOTIVATION))));

        return builder.build();
    }

    public Tool mockTool() {

        Tool.Builder builder = Tool.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        builder.name(random.word());
        fake.coinFlip(() -> builder.description(random.description()));
        fake.coinFlip(() -> builder.toolVersion(String.format("%s.%s.%s", random.integer(0, 5), random.integer(0, 5), random.integer(0, 5))));
        fake.coinFlip(() -> fake.repeatBetween(0, 15, () -> builder.addAlias(random.word())));
        fake.coinFlip(() -> fake.repeatBetween(0, 15, () -> builder.addKillChainPhase(mockKillChainPhase())));
        fake.coinFlip(() -> fake.repeatBetween(0, 15, () -> builder.addToolType(random.elementFrom(TOOL_TYPE))));
        fake.coinFlip(() -> builder.toolVersion(random.word()));

        return builder.build();
    }

    public Vulnerability mockVulnerability() {

        Vulnerability.Builder builder = Vulnerability.builder();

        // ## --- common

        var created = random.instant();
        fake.coinFlip(() -> {
            builder.created(created);
            fake.coinFlip(() -> builder.modified(random.instantAfter(created)));
        });
//        fake.coinFlip(() -> builder.createdByRef(mockIdentity()));
        fake.coinFlip(() -> builder.revoked(true));
        fake.coinFlip(() -> builder.labels(generateRandomLabels()));
        fake.coinFlip(() -> builder.confidence(random.integer(0, 100)));
        fake.coinFlip(() -> builder.lang(random.word()));
        fake.coinFlip(() -> builder.customProperties(generateCustomProperties()));
        fake.coinFlip(() -> fake.repeatUpTo(10, () -> builder.addExternalReferences(mockExternalReference())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addObjectMarkingRef(mockMarkingDefinition())));
//        fake.coinFlip(() -> fake.repeatFor( 4, () -> builder.addGranularMarking(mockGranularMarking())));

        // ## --- specific

        builder.name(random.word());
        fake.coinFlip(() -> builder.description(random.description()));

        return builder.build();
    }




    Map<String, Object> generateCustomProperties() {
        var map = new HashMap<String, Object>();
        fake.repeatUpTo(20, () -> {
            var key = random.prefixedWord("x_");
            switch (random.integer(0, 5)) {
                case 0 -> map.put(key, random.word());
                case 1 -> map.put(key, random.csvWords(100));
                case 2 -> map.put(key, random.integer( 999999));
                case 3 -> map.put(key, random.doubleVal(999999.0));
                case 4 -> map.put(key, random.bool());
                case 5 -> {
                    var map1 = new HashMap<String, String>();
                    fake.repeatUpTo(30, () -> map1.put(random.word(), random.csvWords(10)));
                    map.put(key, map1);
                }
            }
        });
        return map;
    }

    ExternalReference mockExternalReference() {
        ExternalReference.Builder builder = ExternalReference.builder().sourceName(random.word());
        fake.ifTrue(50, () -> builder.description(random.spacedWords(10)));
        fake.ifTrue(50, () -> builder.url(random.url()));
        fake.ifTrue(50, () -> builder.putHash("MD5", random.md5()));
        fake.ifTrue(20, () -> builder.putHash("SHA-1", random.sha1()));
        fake.ifTrue(20, () -> builder.putHash("SHA-256", random.sha256()));
        fake.ifTrue(20, () -> builder.putHash("SHA-512", random.sha512()));
        fake.ifTrue(50, () -> builder.externalId(random.uuid()));
        return builder.build();
    }

    Set<String> generateRandomLabels() {
        Set<String> labels = new HashSet<>();
        fake.repeatUpTo(19, () -> labels.add(random.word()));
        return labels;
    }

    KillChainPhase mockKillChainPhase() {
        return KillChainPhase.builder()
            .killChainName(random.joinedWords(1,5,"-"))
            .phaseName(random.joinedWords(1,5,"-"))
            .build();
    }

//    MarkingDefinition mockMarkingDefinition() {
//        MarkingDefinition.Builder builder = MarkingDefinition.builder();
//
//        Instant objectCreated = randomInstant(commonLowerDate, Instant.now());
//        smock.runIfTrue(50, () -> builder.created(objectCreated));
//
//        String type = mock.fromStrings(List.of("tlp", "statement")).get();
//        builder.definitionType(type);
//        switch (type) {
//            case "tlp" -> builder.definition(mockTlpMakingObject());
//            case "statement" -> builder.definition(mockStatementMarkingObject());
//        }
//
//        smock.runIfTrue(50, () -> builder.customProperties(generateCustomProperties()));
//
//        return builder.build();
//    }

//    GranularMarking mockGranularMarking() {
//        GranularMarking.Builder builder = GranularMarking.builder();
//
//        builder.markingRef(mockMarkingDefinition());
//        smock.randomRepeat(0, 10, () -> builder.addSelector(mock.words().get()));
//        smock.runIfTrue(50, () -> builder.customProperties(generateCustomProperties()));
//
//        return builder.build();
//    }

}
