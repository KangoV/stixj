package io.kangov.stix.json.converters.dehydrated;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.kangov.stix.Stix;
import io.kangov.stix.core.sdo.SdoObject;

import java.util.Optional;

/**
 * Generates a Dehydrated Domain Object based on an ID.
 */
public class DomainObjectOptionalConverter extends StdConverter<String, Optional<SdoObject>> {

    @Override
    public Optional<SdoObject> convert(String value) {
            String[] parsedValue = value.split("--");

            if (parsedValue.length == 2){
                ObjectMapper mapper = Stix.get().parser().objectMapper();
                ObjectNode node = mapper.createObjectNode();

                node.put("type", parsedValue[0]);
                node.put("id", value);
                node.put("hydrated", false);

                try {
                    return Optional.ofNullable(mapper.treeToValue(node, SdoObject.class));
                    //@TODO add more logic

                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException("Cannot Parse Json", e);
                }

            } else {
                throw new IllegalArgumentException("Id is not valid format, Cannot Parse Value: " + value);
        }
    }
}