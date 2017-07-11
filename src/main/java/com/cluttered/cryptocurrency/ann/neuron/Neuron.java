package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class Neuron {

    private final BigFloat bias;
    private final List<BigFloat> weights;
    private final Activation activation;

    Neuron(final BigFloat bias, final List<BigFloat> weights, final Activation activation) {
        this.bias = bias;
        this.weights = weights;
        this.activation = activation;
    }
}