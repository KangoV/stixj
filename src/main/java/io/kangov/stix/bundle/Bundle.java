package io.kangov.stix.bundle;

import java.util.Set;

/**
 * bundle
 * <p>
 * A Bundle is a collection of arbitrary STIX Objects and Marking Definitions grouped together in a single container.
 * 
 */
@SuppressWarnings("unused")
public record Bundle(

    Set<Bundleable> getObjects

) {
}
