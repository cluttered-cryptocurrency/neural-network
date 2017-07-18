package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author cluttered.code@gmail.com
 */
public class Neuron {

    private static final Logger LOG = LoggerFactory.getLogger(Neuron.class);

    private final Double bias;
    private final List<Double> weights;
    private final Activation activation;

    Neuron(final Double bias, final List<Double> weights, final Activation activation) {
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
     * @param inputs The {@code list} of {@code Double} objects used to calculate the {@code Neuron} object's output.
     * @return The output of this {@code Neuron}.
     */
    public double fire(final List<Double> inputs) {
        final long startTimeNanos = System.nanoTime();
        LOG.debug("Fire Neuron");
        final double biasDotProduct = dotProductWithWeights(inputs) + bias;
        final double result = activation.evaluate(biasDotProduct);
        LOG.trace("Neuron Time: {}nanos", System.nanoTime() - startTimeNanos);
        return result;
    }

    private double dotProductWithWeights(final List<Double> inputs) {
        if (inputs.size() != weights.size()) {
            throw new IllegalArgumentException("inputs (" + inputs.size() + ") and weights (" + weights.size() + ") must have the same number of elements");
        }
        return IntStream.range(0, inputs.size())
                .mapToDouble(i -> inputs.get(i) * weights.get(i))
                .sum();
    }
}