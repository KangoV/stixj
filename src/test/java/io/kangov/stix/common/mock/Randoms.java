package io.kangov.stix.common.mock;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import net.andreinc.mockneat.MockNeat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
@SuppressWarnings("unused")
public class Randoms {

    public record Range(int lower, int upper) {
        public static Range of(int lower, int upper) {
            return new Range(lower, upper);
        }
    }

    static Instant LOWER_INSTANT = Instant.ofEpochMilli(LocalDate.of(2000, 1, 1).toEpochDay());
    static Instant UPPER_INSTANT = Instant.now();

    private final MockNeat mock;

    @Inject
    public Randoms(MockNeat mock) {
        this.mock = mock;
    }

    public int integer()                     { return mock.ints().get(); }
    public int integer(int upper)            { return mock.ints().range(0, upper).get(); }
    public int integer(int lower, int upper) { return mock.ints().range(lower, upper).get(); }
    public int integer(Range range)          { return mock.ints().range(range.lower(), range.upper()).get(); }
    public int positiveInteger()             { return mock.ints().range(0, Integer.MAX_VALUE).get(); }
    public int negativeInteger()             { return mock.ints().range(Integer.MIN_VALUE, Integer.MAX_VALUE).get(); }

    public long longVal()                       { return mock.longs().get(); }
    public long longVal(long upper)             { return mock.longs().range(0L, upper).get(); }
    public long longVal(long lower, long upper) { return mock.longs().range(lower, upper).get(); }
    public long positiveLong()                  { return mock.longs().range(0L, Long.MAX_VALUE).get(); }
    public long negativeLong()                  { return mock.longs().range(Long.MIN_VALUE, Long.MAX_VALUE).get(); }

    public double doubleVal()                           { return mock.doubles().get(); }
    public double doubleVal(double upper)               { return mock.doubles().range(0, upper).get(); }
    public double doubleVal(double lower, double upper) { return mock.doubles().range(lower, upper).get(); }
    public double positiveDouble()                      { return mock.doubles().range(0, Double.MAX_VALUE).get(); }
    public double negativeDouble()                      { return mock.doubles().range(Double.MIN_VALUE, Double.MAX_VALUE).get(); }

    public String joinedWords(Range range, String sep) { return joinedWords(range.lower(), range.upper(), sep);  }
    public String joinedWords(int upper, String sep)   { return joinedWords(1, upper, sep);  }
    public String joinedWords(int lower, int upper, String separator) {
        var count = integer(lower, upper);
        return mock.words().accumulate(count, separator).get();
    }

    public String word() { return mock.words().get(); }

    public String csvWords(int lower, int upper) { return joinedWords(lower, upper, ","); }
    public String csvWords(Range range)          { return csvWords(range.lower(), range.upper()); }
    public String csvWords(int upper)            { return csvWords(1, upper); }

    public String spacedWords(int lower, int upper) { return joinedWords(lower, upper, " "); }
    public String spacedWords(Range range)          { return spacedWords(range.lower(), range.upper()); }
    public String spacedWords(int upper)            { return spacedWords(1, upper); }

    public String kebabWords(int lower, int upper) { return joinedWords(lower, upper, "-"); }
    public String kebabWords(Range range)          { return kebabWords(range.lower(), range.upper()); }
    public String kebabWords(int upper)            { return kebabWords(1, upper); }

    public Iterable<String> iterableOfWords(int count) {
        var list = new ArrayList<String>();
        for (int i=0; i<count; i++) {
            list.add(word());
        }
        return list;
    }

    public String prefixedWord(String pre) { return mock.words().prepend(pre).get(); }

    public Instant instantAfter(Instant upper)  { return instantBetween(LOWER_INSTANT, upper); }
    public Instant instantBefore(Instant lower) { return instantBetween(lower, UPPER_INSTANT); }
    public Instant instant()                    { return instantBetween(LOWER_INSTANT, UPPER_INSTANT);  }
    public Instant instantBetween(Instant lower, Instant upper){
        return Instant.ofEpochMilli(ThreadLocalRandom.current()
            .longs(lower.toEpochMilli(), upper.toEpochMilli())
            .findAny()
            .getAsLong());
    }


    public String sha256() { return mock.hashes().sha256().get(); }
    public String sha512() { return mock.hashes().sha512().get(); }
    public String sha1()   { return mock.hashes().sha1().get(); }
    public String md5()    { return mock.hashes().md5().get(); }


    public Boolean bool()         { return mock.bools().probability(50).get(); }
    public Boolean bool(int i)    { return mock.bools().probability(i).get(); }
    public Boolean bool(double i) { return mock.bools().probability(i).get(); }

    public String uuid() { return mock.uuids().get(); }

    public String fullName(double v) { return mock.names().full(v).get(); }
    public String fullName()         { return fullName(33.33); }

    public String email() { return mock.emails().get(); }

    public String department() { return mock.departments().get(); }

    public String stringFrom(Collection<String> list) { return mock.fromStrings(List.copyOf(list)).get(); }

    public String name() { return spacedWords(3); }

    public String description() { return spacedWords(10); }

    public String hex() { return mock.chars().hex().get().toString(); }

    public String mime() { return mock.mimes().get(); }

    public String domain() { return mock.domains().get(); }

    public String ipv4() { return mock.ipv4s().get(); }

    public String ipv6() { return mock.iPv6s().get(); }

    public String mac() { return mock.macs().get(); }

    public String lastName() { return mock.names().last().get(); }

    public float floatValue(int i, long l) { return mock.floats().range(i, l).get(); }

    public String city() {
        return mock.cities().us().get();
    }

    public String countryIso2() { return mock.countries().iso2().get(); }

    public String floatAsString(int i, long l) { return Float.toString(floatValue(i, l)); }

    public <T> T elementFrom(Iterable<T> iterable) {
        List<T> list = new ArrayList<T>();
        iterable.forEach(list::add);
        return list.get(mock.ints().range(0, list.size()).get());
    }

    public <T> T elementFromList(List<T> elements) {
        return elements.get(mock.ints().range(0, elements.size()).get());
    }

    public <T> T elementFromSet(Set<T> elements) {
        var skip = mock.ints().range(0, elements.size()).get();
        return elements.stream().skip(skip).findFirst().orElse(null);

    }

    public String url() { return mock.urls().get(); }


}
