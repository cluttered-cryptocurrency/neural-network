package com.cluttered.cryptocurrency;

import com.cluttered.cryptocurrency.ann.Activation;
import com.cluttered.cryptocurrency.ann.Neuron;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author cluttered.code@gmail.com
 */
public class RandomGenerator {

    private static final Random RANDOM = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());

    private static double weightRange = 1;
    private static double biasRange = 10;

    public static double randomBetween(final double min, final double max) {
        return RANDOM.nextDouble() * (max - min) + min;
    }

    public static double random() {
        return RANDOM.nextDouble();
    }

    public static double randomBias() {
        return randomBetween(-biasRange, biasRange);
    }

    public static double randomLeakage() {
        return random();
    }

    public static List<Double> randomWeights(final int size) {
        return IntStream.range(0, size)
                .mapToDouble(i -> randomWeight())
                .boxed()
                .collect(Collectors.toList());
    }

    public static double randomWeight() {
        return randomBetween(-weightRange, weightRange);
    }

    public static List<Neuron> randomNeurons(final int inputSize, final int size, final Activation activation) {
        return IntStream.range(0, size)
                .mapToObj(i -> Neuron.generate(inputSize, activation))
                .collect(Collectors.toList());
    }

    public static boolean coinFlip() {
        return RANDOM.nextBoolean();
    }

    public static double getWeightRange() {
        return weightRange;
    }

    public static void setWeightRange(final double newWeightRange) {
        weightRange = newWeightRange;
    }

    public static double getBiasRange() {
        return biasRange;
    }

    public static void setBiasRange(final double newBiasRange) {
        biasRange = newBiasRange;
    }
}
