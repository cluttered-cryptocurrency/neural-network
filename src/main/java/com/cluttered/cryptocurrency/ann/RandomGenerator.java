package com.cluttered.cryptocurrency.ann;

import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random();

    public static Double generateRandomWeight() {
        return boundedRandom();
    }

    public static Double generateRandomBias() {
        return boundedRandom();
    }

    private static Double boundedRandom() {
        return random.nextDouble() * 2 - 1;
    }

    public static int generateRandomIntBoundBy(final int bound) {
        return random.nextInt(bound);
    }
}
