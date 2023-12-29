package io.kangov.stix.util;

import io.kangov.stix.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBases {

    protected static final int MOCK_COUNT = 200;

    protected static final String RESOURCES_ROOT        = "/v21/stix/";
    protected static final String SDO_RESOURCES_ROOT    = RESOURCES_ROOT + "sdo/";
    protected static final String SCO_RESOURCES_ROOT    = RESOURCES_ROOT + "sco/";
    protected static final String BUNDLE_RESOURCES_ROOT = RESOURCES_ROOT + "bundle/";

    private static final Logger log = LoggerFactory.getLogger(TestBases.class);

    protected void checkBundle(Parser parser, String bundle) {
        log.debug("reading initial bundle...");
        var obj1  = parser.read(bundle);
        log.debug("writing bundle...");
        var json2 = parser.write(obj1);
        log.debug("reading bundle again...");
        var obj2  = parser.read(json2);
        assertThat(obj1).isEqualTo(obj2);
        log.debug("ok");
    }

}
