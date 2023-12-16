package io.kangov.stix.v21.core.sro.objects;

import io.kangov.stix.Parser;
import io.kangov.stix.Stix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class RelationshipTest {

    private static final Logger log = LoggerFactory.getLogger(RelationshipTest.class);

    private static final String BUNDLE_STRING = """
    {
        "id": "bundle--6c9a1180-994e-4082-9a11-80994e308253",
        "type": "bundle",
        "objects": [
            {
                "type": "infrastructure",
                "spec_version": "2.1",
                "id": "infrastructure--d09c50cf-5bab-465e-9e2d-543912148b73",
                "created": "2016-11-22T09:22:30.000Z",
                "modified": "2016-11-22T09:22:30.000Z",
                "name": "Example Target List Host",
                "infrastructure_types": ["hosting-target-lists"]
            },
            {
                "type": "relationship",
                "spec_version": "2.1",
                "id": "relationship--37ac0c8d-f86d-4e56-aee9-914343959a4c",
                "created": "2016-11-23T08:17:27.000Z",
                "modified": "2016-11-23T08:17:27.000Z",
                "relationship_type": "uses",
                "source_ref": "malware--3a41e552-999b-4ad3-bedc-332b6d9ff80c",
                "target_ref": "infrastructure--d09c50cf-5bab-465e-9e2d-543912148b73"
            },
            {
                "type": "malware",
                "spec_version": "2.1",
                "id": "malware--3a41e552-999b-4ad3-bedc-332b6d9ff80c",
                "created": "2016-11-12T14:31:09.000Z",
                "modified": "2016-11-12T14:31:09.000Z",
                "is_family": true,
                "malware_types": [
                    "bot"
                ],
                "name": "IMDDOS"
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
        var bundle = parser.readBundle(BUNDLE_STRING);
        assertThat(bundle).isNotNull();
    }

}
