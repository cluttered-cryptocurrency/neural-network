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

    private Double bias;
    private List<Double> weights;
    private Activation activation;

    private NeuronBuilder() {
    }

    static Neuron random(final int inputSize, final Set<Activation> eligibleActivations) {
        if (inputSize < 1)
            throw new IllegalArgumentException("Neuron must contain at least one input");

        return NeuronBuilder.create()
                .randomWeights(inputSize)
                .randomActivationOf(eligibleActivations)
                .randomBias()
                .build();
    }

    private static NeuronBuilder create() {
        return new NeuronBuilder();
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

    private Double randomBoundedDouble() {
        // bound: [-1, 1)
        return RANDOM.nextDouble() * 2 - 1;
    }

    private Neuron build() {
        if (weights == null || weights.isEmpty())
            throw new IllegalStateException("Neuron must contain at least one weight");
        if (activation == null)
            throw new IllegalStateException("Neuron must contain a valid Activation");

        return new Neuron(bias, weights, activation);
    }
}