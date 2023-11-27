package io.kangov.stix.util;

import io.kangov.stix.bundle.Bundleable;
import io.micronaut.core.type.Argument;
import io.micronaut.serde.*;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.util.Objects;

//@Singleton // (1)
public class BundleableSerde implements Serde<Bundleable> { // (2)
    @Override
    public Bundleable deserialize(Decoder decoder, DecoderContext context, Argument<? super Bundleable> type) throws IOException {
        try (Decoder array = decoder.decodeArray()) { // (3)
            System.out.println("deserialize");
            return null; // (4)
        }
    }

    @Override
    public void serialize(Encoder encoder, EncoderContext context, Argument<? extends Bundleable> type, Bundleable value) throws IOException {
        Objects.requireNonNull(value, "Point cannot be null"); // (5)

        try (Encoder array = encoder.encodeObject(type)) { // (6)
            System.out.println("serialize");

        }
    }
}