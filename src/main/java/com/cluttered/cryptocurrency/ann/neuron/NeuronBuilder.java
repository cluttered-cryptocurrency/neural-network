package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilder {

    private static final Random RANDOM = new Random();

    private double bias;
    private List<Double> weights;
    private Activation activation;

    private NeuronBuilder() {
    }

    static Neuron random(final int inputSize, final Set<Activation> eligibleActivations) {
        if (inputSize < 1)
            throw new IllegalArgumentException("Neuron must contain at least one input");

        final Set<Activation> activations = eligibleActivations != null
                ? eligibleActivations
                : Collections.emptySet();

        return new NeuronBuilder()
                .randomWeights(inputSize)
                .randomActivationOf(activations)
                .randomBias()
                .build();
    }

    private NeuronBuilder randomWeights(final int inputSize) {
        weights = IntStream.range(0, inputSize)
                .mapToDouble(i -> randomBoundedDouble())
                .boxed()
                .collect(Collectors.toList());
        return this;
    }

    private NeuronBuilder randomActivationOf(final Set<Activation> eligibleActivations) {
        // All Activations are eligible if none are specified
        final List<Activation> activations = !eligibleActivations.isEmpty()
                ? new ArrayList<>(eligibleActivations)
                : Arrays.asList(Activation.values());

        final int index = RANDOM.nextInt(activations.size());
        activation = activations.get(index);
        return this;
    }

    private NeuronBuilder randomBias() {
        bias = randomBoundedDouble();
        return this;
    }

    private double randomBoundedDouble() {
        // bound: [-1, 1)
        return RANDOM.nextDouble() * 2 - 1;
    }

    private Neuron build() {
        return new Neuron(bias, weights, activation);
    }
}