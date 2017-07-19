package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.ann.RandomGenerator.*;
import static com.cluttered.cryptocurrency.ann.activation.Activation.*;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilder {

    // Defaults to InputNeuron
    private double bias = 0;
    private List<Double> weights = Collections.singletonList(1.0);
    private Activation activation = LINEAR;

    private NeuronBuilder() {
    }

    protected static NeuronBuilder create() {
        return new NeuronBuilder();
    }


    public NeuronBuilder randomBias() {
        bias = generateRandomBias();
        return this;
    }

    public NeuronBuilder bias(final double bias) {
        this.bias = bias;
        return this;
    }

    public NeuronBuilder randomWeights(final int inputSize) {
        return weights(IntStream.range(0, inputSize)
                .mapToDouble(i -> generateRandomWeight())
                .boxed()
                .collect(Collectors.toList()));
    }

    public NeuronBuilder weights(final List<Double> weights) {
        this.weights = weights;
        return this;
    }

    public NeuronBuilder weights(final Double... weights) {
        return weights(Arrays.asList(weights));
    }

    public NeuronBuilder randomActivation() {
        return activation(Activation.random());
    }

    public NeuronBuilder randomActivationOf(final Activation... eligibleActivations) {
        if (eligibleActivations.length == 0)
            return randomActivation();
        final int index = generateRandomIntBoundBy(eligibleActivations.length);
        return activation(eligibleActivations[index]);
    }

    public NeuronBuilder linear() {
        return activation(LINEAR);
    }

    public NeuronBuilder sigmoid() {
        return activation(SIGMOID);
    }

    public NeuronBuilder tanH() {
        return activation(TAN_H);
    }

    public NeuronBuilder activation(final Activation activation) {
        this.activation = activation;
        return this;
    }

    public Neuron random(final int inputSize, final Activation... eligibleActivations) {
        randomBias();
        randomWeights(inputSize);
        randomActivationOf(eligibleActivations);
        return build();
    }

    public Neuron build() {
        if (weights == null || weights.isEmpty())
            throw new IllegalStateException("Neuron must contain at least one weight");
        if (activation == null)
            throw new IllegalStateException("Neuron must contain a valid Activation");
        return new Neuron(bias, weights, activation);
    }
}