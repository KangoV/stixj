package io.kangov.stix.common.mock;

import io.kangov.stix.common.mock.Randoms.Range;
import jakarta.inject.Singleton;

@Singleton
@SuppressWarnings("unused")
public class Faker {

    private final Randoms random;

    public Faker(Randoms random) {
        this.random = random;
    }

    void ifTrueElse(int probability, Runnable action, Runnable elseAction) {
        if (random.bool(probability)) {
            action.run();
        } else {
            elseAction.run();
        }
    }

    void ifTrue(double probability, Runnable action) {
        if (random.bool(probability)) action.run();
    }

    void ifTrue(int probability, Runnable action) {
        if (random.bool(probability)) action.run();
    }

    void coinFlip(Runnable action) {
        if (random.bool()) action.run();
    }

    void repeatUpTo(int count, Runnable r) {
        repeatBetween(1, count, r);
    }

    void repeatBetween(Range range, Runnable r) {
        repeatUpTo(random.integer(range), r);
    }

    void repeatBetween(int begin, int end, Runnable r) {
        int rn = random.integer(begin, end);
        for (int i=0; i<rn; i++) {
            r.run();
        }
    }


}
