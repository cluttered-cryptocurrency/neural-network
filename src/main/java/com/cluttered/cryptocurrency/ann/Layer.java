package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ga.GeneticElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.ann.RandomGenerator.randomNeurons;

/**
 * @author cluttered.code@gmail.com
 */
public class Layer implements GeneticElement<Layer> {

    private static final Logger LOG = LoggerFactory.getLogger(Layer.class);

    private final List<Neuron> neurons;

    private Layer(final List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public static Layer generate(final int inputSize, final int size, final Activation activation) {
        return new Layer(randomNeurons(inputSize, size, activation));
    }

    public List<Double> fire(final List<Double> inputs) {
        final long startTimeNanos = System.nanoTime();
        LOG.debug("Fire Layer");
        LOG.trace("Inputs: {}", inputs);
        final List<Double> results = neurons.parallelStream()
                .map(neuron -> neuron.fire(inputs))
                .collect(Collectors.toList());
        LOG.trace("Layer Time: {}nanos", System.nanoTime() - startTimeNanos);
        LOG.trace("====================");
        return results;
    }

    public int size() {
        return neurons.size();
    }

    @Override
    public Layer mutate(final double mutationRate) {
        final List<Neuron> mutatedNeurons = neurons.stream()
                .map(neuron -> neuron.mutate(mutationRate))
                .collect(Collectors.toList());
        return new Layer(mutatedNeurons);
    }

    @Override
    public Layer crossover(final Layer mate) {
        final List<Neuron> crossoverNeurons = IntStream.range(0, neurons.size())
                .mapToObj(i -> neurons.get(i).crossover(mate.neurons.get(i)))
                .collect(Collectors.toList());
        return new Layer(crossoverNeurons);
    }
}