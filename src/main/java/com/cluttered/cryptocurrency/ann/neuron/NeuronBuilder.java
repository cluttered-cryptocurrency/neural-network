package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.ann.activation.Activation.*;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilder {

    private static final Random random = new Random();

    // Defaults to InputNeuron
    private double bias = 0;
    private List<Double> weights = Collections.singletonList(1.0);
    private Activation activation = LINEAR;

    private NeuronBuilder() {
    }

    protected static NeuronBuilder create() {
        return new NeuronBuilder();
    }

    public static Double boundedRandom() {
        return random.nextDouble() * 2 - 1;
    }

    public NeuronBuilder randomBias() {
        bias = boundedRandom();
        return this;
    }

    public NeuronBuilder bias(final double bias) {
        this.bias = bias;
        return this;
    }

    public NeuronBuilder randomWeights(final int inputSize) {
        return weights(IntStream.range(0, inputSize)
                .mapToDouble(i -> boundedRandom())
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

    public NeuronBuilder randomActivation(final Activation... eligible) {
        if (eligible.length == 0)
            return randomActivation();
        return activation(eligible[random.nextInt(eligible.length)]);
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

    public Neuron random(final int inputSize, final Activation... eligible) {
        randomBias();
        randomWeights(inputSize);
        randomActivation(eligible);
        return build();
    }

    public Neuron build() {
        return new Neuron(bias, weights, activation);
    }
}