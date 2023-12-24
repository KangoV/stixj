package io.kangov.stix.util;

import io.kangov.stix.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBases {

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
