package io.kangov.stix.common.property;

public interface Extensions {

//    /**
//     * Multiple extensions can be added, but only 1 instance of a specific extension can be added.
//     * @return
//     */
//    // @TODO Add validation to ensure that only 1 instance of each extension is applied as per the spec
//    @JsonProperty("extensions")
//    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
//    @JsonPropertyDescription("""
//        Specifies any extensions of the object, as a dictionary.
//        Dictionary keys MUST identify the extension type by name.
//        The corresponding dictionary values MUST contain the contents of the extension instance.
//        This property MUST NOT be used on any STIX Objects other than SCOs.
//        """)
//    @JsonSerialize(using = CyberObservableExtensionsFieldSerializer.class)
//    @JsonDeserialize(using = CyberObservableExtensionsFieldDeserializer.class)
//    Set<CyberObservableExtension> getExtensions();
//
}
