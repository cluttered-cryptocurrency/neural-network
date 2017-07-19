package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.cluttered.cryptocurrency.ann.GsonConstant.GSON;

/**
 * @author cluttered.code@gmail.com
 */
public class Layer {

    private static final Logger LOG = LoggerFactory.getLogger(Layer.class);

    private final List<Neuron> neurons;

    Layer(final List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public static Layer fromJson(final String json) {
        return GSON.fromJson(json, Layer.class);
    }

    public static LayerBuilder builder() {
        return LayerBuilder.create();
    }

    public static Layer random(final int inputSize, final int neuronCount, final Activation... eligible) {
        return LayerBuilder.create().random(inputSize, neuronCount, eligible);
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

    public String toJson() {
        return GSON.toJson(this);
    }
}