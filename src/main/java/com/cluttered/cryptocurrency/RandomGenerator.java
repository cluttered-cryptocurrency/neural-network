package com.cluttered.cryptocurrency;

import com.cluttered.cryptocurrency.ann.ActivationFunction;
import com.cluttered.cryptocurrency.ann.Neuron;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author cluttered.code@gmail.com
 */
public class RandomGenerator {

    private static final Random RANDOM = new Random();

    public static double randomBetween(final int min, final int max) {
        return RANDOM.nextDouble() * (max - min + 1) + min;
    }

    public static double random() {
        return RANDOM.nextDouble();
    }

    public static double randomBias() {
        return RANDOM.nextDouble();
    }

    public static double randomLeakage() {
        return RANDOM.nextDouble();
    }

    public static List<Double> randomWeights(final int size) {
        return IntStream.range(0, size)
                .mapToDouble(i -> randomWeight())
                .boxed()
                .collect(Collectors.toList());
    }

    public static double randomWeight() {
        return RANDOM.nextDouble();
    }

    public static List<Neuron> randomNeurons(final int inputSize, final int size, final ActivationFunction activation) {
        return IntStream.range(0, size)
                .mapToObj(i -> Neuron.generate(inputSize, activation))
                .collect(Collectors.toList());
    }

    public static boolean coinFlip() {
        return RANDOM.nextBoolean();
    }
}
