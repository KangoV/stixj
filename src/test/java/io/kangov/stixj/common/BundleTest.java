package io.kangov.stixj.common;

import io.kangov.stixj.Stix;
import io.kangov.stixj.common.bundle.Bundle;
import io.kangov.stixj.common.objects.sco.DomainName;
import io.micronaut.serde.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BundleTest {

    private Stix stix;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.stix = Stix.get();
        assertThat(stix).isNotNull();
        this.objectMapper = stix.objectMapper();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    void testObjectToString() throws Exception {

//        var bundle = Bundle.builder()
//            .addObjects(DomainName.builder()
////                .id("3ef9dbc6-7414-45ad-b9db-c67414f5ad36")
//                .value("mydomain.com")
//                .build())
//            .build();
//
//        var str = bundle.json();
//
//        assertThat(str).isNotNull();
//
//        System.out.println(str);
    }
}
