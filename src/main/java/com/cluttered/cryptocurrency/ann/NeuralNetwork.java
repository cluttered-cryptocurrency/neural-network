package com.cluttered.cryptocurrency.ann;

import ch.obermuhlner.math.big.BigFloat;

import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuralNetwork {

    private final long inputSize;
    private final List<Layer> layers;

    private NeuralNetwork(final int inputSize, final List<Layer> layers) {
        this.inputSize = inputSize;
        this.layers = layers;
    }

    public List<BigFloat> fire(final List<BigFloat> inputs) {
        if (inputs.size() != inputSize)
            throw new IllegalArgumentException("NeuralNetwork accepts " + inputSize + " inputs but received " + inputs.size());

        List<BigFloat> layerInputs = inputs;
        for (final Layer layer : layers) {
            layerInputs = layer.fire(layerInputs);
        }
        return layerInputs;
    }
}