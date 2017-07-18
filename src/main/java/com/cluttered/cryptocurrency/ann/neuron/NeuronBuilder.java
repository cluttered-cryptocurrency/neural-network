package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public NeuronBuilder bias(final double bias) {
        this.bias = bias;
        return this;
    }

    public NeuronBuilder weights(final List<Double> weights) {
        this.weights = weights;
        return this;
    }

    public NeuronBuilder weights(final Double... weights) {
        return weights(Arrays.asList(weights));
    }

    public NeuronBuilder activation(final Activation activation) {
        this.activation = activation;
        return this;
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

    public Neuron build() {
        return new Neuron(bias, weights, activation);
    }
}