package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.activation.Activation.*;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilder {

    // Defaults to InputNeuron
    private BigDecimal bias = ZERO;
    private List<BigDecimal> weights = Collections.singletonList(ONE);
    private Activation activation = LINEAR;

    private NeuronBuilder() {
    }

    protected static NeuronBuilder create() {
        return new NeuronBuilder();
    }

    public NeuronBuilder bias(final BigDecimal bias) {
        this.bias = bias;
        return this;
    }

    public NeuronBuilder weights(final List<BigDecimal> weights) {
        this.weights = weights;
        return this;
    }

    public NeuronBuilder weights(final BigDecimal... weights) {
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