package io.kangov.stix.v21.core.sro.objects;

import io.kangov.stix.parser.Parser;
import io.kangov.stix.Stix;
import io.kangov.stix.v21.TestBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class SightingTest {

    private static final Logger log = LoggerFactory.getLogger(SightingTest.class);


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
        var bundle = parser.readBundle(TestBundle.BUNDLE);
        assertThat(bundle).isNotNull();
    }

}
