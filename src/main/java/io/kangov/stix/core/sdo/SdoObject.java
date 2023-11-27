package io.kangov.stix.core.sdo;

import io.kangov.stix.bundle.Bundleable;
import io.kangov.stix.bundle.BundleableBuilder;
import io.kangov.stix.common.type.ExternalReference;

import java.time.Instant;
import java.util.*;
import java.util.function.UnaryOperator;

public interface SdoObject extends Bundleable {

    String specVersion();
    Optional<String> createdByRef();
    Instant created();
    Instant modified();
    Optional<Boolean> revoked();
    Set<String> labels();
    Optional<Integer> confidence();
    Optional<String> lang();
    Set<ExternalReference> externalReferences();

    // TODO: object_marking_refs (Set)
    // TODO: granualr_markings (Set)
    // TODO: extensions (Map)

    @SuppressWarnings({"unused"})
     abstract class SdoBuilder<B> extends BundleableBuilder<B> {

        protected String specVersion;
        protected String createdByRef;
        protected Instant created;
        protected Instant modified;
        protected Boolean revoked;
        protected Set<String> labels = new HashSet<>();
        protected Integer confidence;
        protected String lang;
        protected Set<ExternalReference> externalReferences = new HashSet<>();

        public B specVersion(String v) { this.specVersion = v; return b(); }
        public B createdByRef(String v) { this.createdByRef = v; return b(); }
        public B created(Instant v) { this.created = v; return b(); }
        public B createdNow() { return created(now()); }
        public B modified(Instant v) { this.modified = v; return b(); }
        public B modifiedNow() { return modified(now()); }
        public B revoked(Boolean v) { this.revoked = v; return b(); }
        public B revoked() { return revoked(true); }
        public B notRevoked() { return revoked(false); }
        public B labels(Iterable<String> elems) { return elements(labels, elems); }
        public B labels(String... elems) { return elements(labels, elems); }
        public B addLabel(String elem) { return addElement(labels, elem); }
        public B addLabels(String... elems) { return addElements(labels, elems); }
        public B addAllLabels(Iterable<String> elems) { return addAllElements(labels, elems); }
        public B zeroConfidence() { return confidence(Integer.valueOf(0)); }
        public B confidence(Integer v) { this.confidence = v; return b(); }
        public B confidence(int v) { this.confidence = v; return b(); }
        public B lang(String v) { this.lang = v; return b(); }
        public B externalReferences(Iterable<ExternalReference> elems) { return elements(externalReferences, elems); }
        public B externalReferences(ExternalReference... elems) { return elements(externalReferences, elems); }
        public B addExternalReference(ExternalReference elem) { return addElement(externalReferences, elem); }
        public B addExternalReferences(ExternalReference... elems) { return addElements(externalReferences, elems); }
        public B addAllExternalReferences(Iterable<ExternalReference> elems) { return addAllElements(externalReferences, elems); }
        public B addExternalReference(UnaryOperator<ExternalReference.Builder> func) {
            return addElement(externalReferences, func.apply(ExternalReference.builder()).build());
        }
        public B addExternalReferences(UnaryOperator<ExternalReference.Builder> ... funcs) {
            for (var func : funcs) {
                addElement(externalReferences, func.apply(ExternalReference.builder()).build());
            }
            return b();
        }

        @Override
        protected B preProcess(String typex) {
            super.preProcess(typex);
            if (specVersion == null) specVersion("2.1");
            if (created == null) createdNow();
            if (modified == null) modifiedNow();
            if (revoked == null) notRevoked();
            return b();
        }

    }

}
