package io.kangov.stixj.common;

import io.kangov.stixj.Stix;
import io.kangov.stixj.common.objects.sco.DomainName;
import io.micronaut.serde.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class ObjectTest {

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

        var object = DomainName.builder()
            .id("6c9a1180-994e-4082-9a11-80994e308253")
            .value("mydomain.com")
            .build();

        var str = objectMapper.writeValueAsString(object);

        assertThat(str).isNotNull();

        System.out.println(str);
    }

    @Test
    void testStringToObject() throws Exception{

        var string = """
            {
                "type": "domain-name",
                "value": "mydomain.com"
            }
            """;

        var object = objectMapper.readValue(string, DomainName.class);

        assertThat(object).isNotNull();

        System.out.println(object);

    }
}
