package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ga.GeneticElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.RandomGenerator.*;

/**
 * @author cluttered.code@gmail.com
 */
public class Neuron implements GeneticElement<Neuron> {

    private static final Logger LOG = LoggerFactory.getLogger(Neuron.class);

    private double bias;
    private double leakage;
    private Activation activation;
    private List<Double> weights;

    public Neuron() {
        // Morphia Constructor
    }

    private Neuron(final double bias, final double leakage, final List<Double> weights, final Activation activation) {
        this.activation = activation;
        this.bias = bias;
        this.leakage = leakage;
        this.weights = weights;
    }

    public static Neuron generate(final int inputSize, final Activation activation) {
        return new Neuron(randomBias(), randomLeakage(), randomWeights(inputSize), activation);
    }

    public double fire(final List<Double> inputs) {
        final long startTimeNanos = System.nanoTime();
        LOG.debug("Fire Neuron");
        final double biasDotProduct = dotProductWithWeights(inputs) + bias;
        final double result = activation.execute(biasDotProduct, leakage);
        LOG.trace("Neuron Time: {}nanos", System.nanoTime() - startTimeNanos);
        return result;
    }

    protected double dotProductWithWeights(final List<Double> inputs) {
        if (inputs.size() != weights.size())
            throw new IllegalArgumentException("inputs (" + inputs.size() + ") and weights (" + weights.size() + ") must have the same number of elements");

        return IntStream.range(0, inputs.size())
                .mapToDouble(i -> inputs.get(i) * weights.get(i))
                .sum();
    }

    @Override
    public Neuron mutate(final double mutationRate) {
        final double mutatedBias = random() < mutationRate ? randomBias() : bias;
        final double mutatedLeakage = random() < mutationRate ? randomLeakage() : leakage;
        final List<Double> mutatedWeights = weights.stream()
                .map(weight -> random() < mutationRate ? randomWeight() : weight)
                .collect(Collectors.toList());
        return new Neuron(mutatedBias, mutatedLeakage, mutatedWeights, activation);
    }

    @Override
    public Neuron crossover(final Neuron mate) {
        final double crossoverBias = coinFlip() ? mate.bias : bias;
        final double crossoverLeakage = coinFlip() ? mate.leakage : leakage;
        final List<Double> crossoverWeights = IntStream.range(0, weights.size())
                .mapToDouble(i -> coinFlip() ? mate.weights.get(i) : weights.get(i))
                .boxed()
                .collect(Collectors.toList());
        return new Neuron(crossoverBias, crossoverLeakage, crossoverWeights, activation);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Neuron neuron = (Neuron) object;

        if (Double.compare(neuron.bias, bias) != 0) return false;
        if (Double.compare(neuron.leakage, leakage) != 0) return false;
        if (activation != neuron.activation) return false;
        return weights.equals(neuron.weights);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(bias);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(leakage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + activation.hashCode();
        result = 31 * result + weights.hashCode();
        return result;
    }
}