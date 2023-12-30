package io.kangov.stix.v21.core.sco.extension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.kangov.stix.v21.common.type.ObjectRef;

import java.io.IOException;
import java.util.HashSet;

public class ScoExtensionsDeserializer extends StdDeserializer<ScoExtensions> {

    public ScoExtensionsDeserializer() {this(null);}

    public ScoExtensionsDeserializer(Class<ObjectRef<?>> vc) {super(vc);}

    @Override
    public ScoExtensions deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        var codec = parser.getCodec();
        var extensionsNode = codec.readTree(parser);
        var fieldnames = extensionsNode.fieldNames();

        var extensions = new HashSet<ScoExtension>();
        while (fieldnames.hasNext()) {
            var extName = fieldnames.next();
            var extNode = (ObjectNode) extensionsNode.get(extName);
            extNode.put("type", extName); // need this so jackson knows what polymorphic type to create
            var extension = codec.treeToValue(extNode, ScoExtension.class);
            extensions.add(extension);
        }
        return ScoExtensions.create(b -> b.extensions(extensions));
    }
}
