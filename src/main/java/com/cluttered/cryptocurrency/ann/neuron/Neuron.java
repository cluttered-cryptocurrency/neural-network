package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.Collections;
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

    /**
     * Fire this {@code Neuron} with the specified {@code input}. The output of this {@code Neuron} is returned.
     *
     * @param input The input used to calculate the {@code Neuron} object's output.
     * @return The output of this {@code Neuron}.
     */
    public BigFloat fire(final BigFloat input) {
        final List<BigFloat> inputs = Collections.singletonList(input);
        return fire(inputs);
    }

    /**
     * Fire with {@code Neuron} with the specified {@code input}. The output of this {@code Neuron} is returned.
     *
     * @param inputs The {@code list} of {@code BigFloat} objects used to calculate the {@code Neuron} object's output.
     * @return The output of this {@code Neuron}.
     */
    public BigFloat fire(final List<BigFloat> inputs) {
        final BigFloat biasDotProduct = dotProductWithWeights(inputs).subtract(bias);
        return activation.evaluate(biasDotProduct);
    }

    private BigFloat dotProductWithWeights(final List<BigFloat> inputs) {
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