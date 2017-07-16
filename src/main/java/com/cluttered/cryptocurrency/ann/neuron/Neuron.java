package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static java.math.BigDecimal.ZERO;

/**
 * @author cluttered.code@gmail.com
 */
public class Neuron {

    private final BigDecimal bias;
    private final List<BigDecimal> weights;
    private final Activation activation;

    Neuron(final BigDecimal bias, final List<BigDecimal> weights, final Activation activation) {
        this.bias = bias;
        this.weights = weights;
        this.activation = activation;
    }

    public static NeuronBuilder builder() {
        return NeuronBuilder.create();
    }

    /**
     * Fire with {@code Neuron} with the specified {@code input}. The output of this {@code Neuron} is returned.
     *
     * @param inputs The {@code list} of {@code BigDecimal} objects used to calculate the {@code Neuron} object's output.
     * @return The output of this {@code Neuron}.
     */
    public BigDecimal fire(final List<BigDecimal> inputs) {
        final BigDecimal biasDotProduct = dotProductWithWeights(inputs).add(bias);
        return activation.evaluate(biasDotProduct);
    }

    private BigDecimal dotProductWithWeights(final List<BigDecimal> inputs) {
        if (inputs.size() != weights.size()) {
            throw new IllegalArgumentException("inputs (" + inputs.size() + ") and weights (" + weights.size() + ") must have the same number of elements");
        }
        return IntStream.range(0, inputs.size())
                .parallel()
                .mapToObj(i -> inputs.get(i).multiply(weights.get(i)))
                .reduce(ZERO, BigDecimal::add);
    }
}