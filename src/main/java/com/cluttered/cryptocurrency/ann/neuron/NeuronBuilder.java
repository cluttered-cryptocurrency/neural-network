package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.NeuralNetworkConstants.ONE;
import static com.cluttered.cryptocurrency.NeuralNetworkConstants.ZERO;
import static com.cluttered.cryptocurrency.ann.activation.Activation.*;

/**
 * @author cluttered.code@gmail.com
 */
class NeuronBuilder {

    // Defaults to InputNeuron
    private BigFloat bias = ZERO;
    private List<BigFloat> weights = Collections.singletonList(ONE);
    private Activation activation = LINEAR;

    private NeuronBuilder() {}

    protected static NeuronBuilder create() {
        return new NeuronBuilder();
    }

    public NeuronBuilder bias(final BigFloat bias) {
        this.bias = bias;
        return this;
    }

    public NeuronBuilder weights(final List<BigFloat> weights) {
        this.weights = weights;
        return this;
    }

    public NeuronBuilder weights(final BigFloat... weights) {
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
        return new NormalNeuron(bias, weights, activation);
    }
}