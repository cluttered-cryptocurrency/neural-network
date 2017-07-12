package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.List;
import java.util.stream.IntStream;

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

    protected BigFloat dotProductWithWeights(final List<BigFloat> inputs) {
        if (inputs.size() != weights.size()) {
            throw new IllegalArgumentException("inputs (" + inputs.size() + ") and weights (" + weights.size() + ") must have the same number of elements");
        }

        return IntStream.range(0, inputs.size())
                .parallel()
                .mapToObj(i -> inputs.get(i).multiply(weights.get(i)))
                .reduce(BigFloat::add)
                .get();
    }
}