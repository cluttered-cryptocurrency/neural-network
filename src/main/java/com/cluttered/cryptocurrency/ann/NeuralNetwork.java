package com.cluttered.cryptocurrency.ann;


import java.math.BigDecimal;
import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuralNetwork {

    private final Long inputSize;
    private final List<Layer> layers;

    public NeuralNetwork(final Long inputSize, final List<Layer> layers) {
        this.inputSize = inputSize;
        this.layers = layers;
    }

    public List<BigDecimal> fire(final List<BigDecimal> inputs) {
        if (inputs.size() != inputSize)
            throw new IllegalArgumentException("NeuralNetwork accepts " + inputSize + " inputs but received " + inputs.size());

        List<BigDecimal> layerInputs = inputs;
        for (final Layer layer : layers) {
            layerInputs = layer.fire(layerInputs);
        }
        return layerInputs;
    }
}