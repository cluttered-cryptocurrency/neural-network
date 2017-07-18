package com.cluttered.cryptocurrency.ann;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuralNetwork {

    private static final Logger LOG = LoggerFactory.getLogger(NeuralNetwork.class);

    private final Integer inputSize;
    private final List<Layer> layers;

    public NeuralNetwork(final Integer inputSize, final List<Layer> layers) {
        this.inputSize = inputSize;
        this.layers = layers;
    }

    public List<Double> fire(final List<Double> inputs) {
        final long startTimeMillis = System.currentTimeMillis();
        LOG.info("########## Fire NeuralNetwork ##########");
        LOG.info("Inputs: {}", inputs);
        if (inputs.size() != inputSize)
            throw new IllegalArgumentException("NeuralNetwork accepts " + inputSize + " inputs but received " + inputs.size());

        List<Double> layerResults = inputs;
        for (final Layer layer : layers) {
            layerResults = layer.fire(layerResults);
        }
        LOG.info("Outputs: {}", layerResults);
        LOG.info("NeuralNetwork Time: {}ms", System.currentTimeMillis() - startTimeMillis);
        return layerResults;
    }
}