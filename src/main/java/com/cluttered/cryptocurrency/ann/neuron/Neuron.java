package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.Iterator;
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

    public static NeuronBuilder builder() {
        return NeuronBuilder.create();
    }

    private BigFloat dotProductWithWeights(final List<BigFloat> inputs) {
        if (inputs.size() != weights.size()) {
            throw new IllegalArgumentException("inputs (" + inputs.size() + ") and weights (" + weights.size() + ") must have the same number of elements");
        }

        final Iterator<BigFloat> weightsIterator = weights.iterator();
        return inputs.stream()
                .map(input -> input.multiply(weightsIterator.next()))
                .reduce(BigFloat::add)
                .get();
    }
}