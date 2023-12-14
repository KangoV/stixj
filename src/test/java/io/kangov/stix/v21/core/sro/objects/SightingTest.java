package io.kangov.stix.v21.core.sro.objects;

import io.kangov.stix.Parser;
import io.kangov.stix.Stix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class SightingTest {

    private static final Logger log = LoggerFactory.getLogger(SightingTest.class);

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
                "where_sighted_refs" : [
                    "identity--6c9a1180-994e-4082-9a11-80994e308253"
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


    private Stix stix;
    private Parser parser;

    @BeforeEach
    void setup() {
        this.stix = Stix.get();
        this.parser = stix.parser();
        assertThat(stix).isNotNull();
    }

//    @Test
//    void testWrite() {
//        var str = parser.writeBundle(BUNDLE_OBJECT);
////        log.debug("To JSON : \n{}", str);
//        assertThat(str).isNotNull();
//    }

    @Test
    void testRead() throws Exception{
        var bundle = parser.readBundle(json);
        assertThat(bundle).isNotNull();
    }

}
