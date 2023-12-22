package io.kangov.stix.v21.core.sdo.objects;

import io.kangov.stix.Parser;
import io.kangov.stix.common.mock.Mocks;
import io.kangov.stix.v21.bundle.Bundle;
import io.kangov.stix.v21.core.sco.objects.XNominetBlock;
import io.kangov.stix.v21.core.sco.objects.XNominetThreatFeedSource;
import io.kangov.stix.v21.core.sro.objects.Sighting;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class ObservedDataTest {

    private static final Logger log = LoggerFactory.getLogger(ObservedDataTest.class);
    private static final int MOCK_COUNT = 200;

    @Inject Mocks mock;
    @Inject Parser parser;
    @Inject Validator validator;

    private String json = """
    {
        "id": "bundle--6c9a1180-994e-4082-9a11-80994e308253",
        "type": "bundle",
        "objects": [
            {
                "id": "sighting--6c9a1180-994e-4082-9a11-80994e308253",
                "type": "sighting",
                "created": "2023-11-15T17:24:14.356165Z",
                "modified": "2023-11-15T17:24:14.356165Z",
                "created_by_ref": "identity--6c9a1180-994e-4082-9a11-80994e308253",
                "sighting_of_ref": "indicator--6dc15502-e387-4073-9098-23de8ac17099",
                "observed_data_refs": [
                    "observed-data-6c9a1180-994e-4082-9a11-80994e308253"
                ]
            },
            {
                "id": "observed-data--6c9a1180-994e-4082-9a11-80994e308253",
                "type": "observed-data",
                "created": "2023-11-15T17:24:14.356165Z",
                "modified": "2023-11-15T17:24:14.356165Z",
                "created_by_ref": "identity--6c9a1180-994e-4082-9a11-80994e308253",
                "first_observed": "2023-10-09T10:01:04.000Z",
                "last_observed": "2023-10-09T10:01:04.000Z",
                "number_observed": 1,
                "objects": {
                    "0": {
                        "type": "x-nominet-block",
                        "qname": "rmansys.ru",
                        "qtype": "A",
                        "qclass": "IN",
                        "src_ip_network_type": "ipv4",
                        "src_ip": "127.0.0.1",
                        "src_port": "52654",
                        "rpz_range": "domain-name",
                        "rpz_range_matched": "rmansys.ru",
                        "rpz_zone": "delta30",
                        "dns_type": "DOH"
                    },
                    "1": {
                        "type": "x-nominet-threat-feed-source",
                        "name": "uk.ncsc.sep.admin",
                        "meta_data": [
                            {
                                "key": "threat_type",
                                "value": "threat"
                            },{
                                "key": "threat",
                                "value": "malware"
                            },{
                                "key": "names",
                                "value": "[\\"Mal/HTMLGen-A\\"]"
                            },{
                                "key": "tags",
                                "value": "[]"
                            },{
                                "key": "raw_feed_meta_data",
                                "value": "{\\"something\\":\\"arbitrary\\"}"
                            }
                        ]
                    }
                }
            },
            {
                "type": "indicator",
                "id": "indicator--6dc15502-e387-4073-9098-23de8ac17099",
                "created": "2023-10-09T10:10:00.137Z",
                "modified": "2023-10-09T10:10:00.137Z",
                "created_by_ref": "identity--6c9a1180-994e-4082-9a11-80994e308253",
                "labels": [
                  "malicious-activity"
                ],
                "pattern": "[ domain-name:value \\u003d \\u0027rmansys.ru\\u0027 ]",
                "valid_from": "2020-02-10T02:00:19.095Z"
            },
            {
                "id": "identity--6c9a1180-994e-4082-9a11-80994e308253",
                "type": "identity",
                "spec_version": "2.1",
                "created": "2023-11-15T17:24:14.356165Z",
                "modified": "2023-11-15T17:24:14.356165Z",
                "name": "ACME Widget, Inc.",
                "description": "ACME Widgets is fictional ;)",
                "identity_class": "organization"
            }
        ]
    }
    """;

    private Bundle.Builder builder;


    @BeforeEach
    void beforeEach() {

        var identity = Identity.create(i -> i
            .created(Instant.now())
            .modified(Instant.now())
            .name("ACME Widget, Inc.")
            .description("ACME Widgets is fictional ;)")
            .identityClass("organization")
            .contactInformation("not_contactable")
        );

        var indicator = Indicator.create(i -> i
            .created(Instant.now())
            .modified(Instant.now())
            .createdByRef(identity)
            .addLabel("malicious-activity")
            .pattern("[ domain-name:value \\u003d \\u0027rmansys.ru\\u0027 ]")
            .validFrom(Instant.now())
        );

        var observedData = ObservedData.create(o -> o
            .created(Instant.now())
            .modified(Instant.now())
            .createdByRef(identity)
            .firstObserved(Instant.now())
            .lastObserved(Instant.now())
            .numberObserved(1)
            .objects(Map.of(
                "0", XNominetBlock.create(x -> x
                    .qname("rmansys.ru")
                    .qType("A")
                    .qClass("IN")
                    .sourceIPNetworkType("ipv4")
                    .sourceIP("127.0.0.1")
                    .sourcePort(52584)
                    .rPZRange("domain-name")
                    .rPZRangeMatched("rmansys.ru")
                    .rPZZone("delta30")
                    .dNSType("DOH")
                ),
                "1", XNominetThreatFeedSource.create(x -> x
                    .name("uk.ncsc.sep.admin")
                    .addMetadata(
                        new XNominetThreatFeedSource.MetadataEntry("threat_type", "threat"),
                        new XNominetThreatFeedSource.MetadataEntry("threat", "malware"),
                        new XNominetThreatFeedSource.MetadataEntry("names", "[\\\"Mal/HTMLGen-A\\\"]"),
                        new XNominetThreatFeedSource.MetadataEntry("tags", "[]"),
                        new XNominetThreatFeedSource.MetadataEntry("raw_feed_meta_data", "{\\\"something\\\":\\\"arbitrary\\\"}")
                    )
                )
            ))
        );

        var sighting = Sighting.create(s -> s
            .created(Instant.now())
            .modified(Instant.now())
            .createdByRef(identity)
            .sightingOfRef(indicator)
            .addObservedDataRef(observedData)
        );

        this.builder = Bundle.builder().addObjects(identity, indicator, observedData, sighting);
    }

    @Test
    void test_String_To_Object() throws Exception{
        var object = parser.readBundle(json);
        assertThat(object).isNotNull();
    }

    @Test
    void test_Object_To_String() throws Exception {
        var bundle = builder.build();
        var string = parser.writeBundle(bundle);
        assertThat(string).isNotNull();
    }

}
