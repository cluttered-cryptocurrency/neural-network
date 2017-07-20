package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import com.cluttered.cryptocurrency.ann.layer.Layer;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkBuilder {

    private int inputSize;
    private List<Layer> layers = new ArrayList<>();

    private NeuralNetworkBuilder(final int inputSize) {
        this.inputSize = inputSize;
    }

    public static NeuralNetworkBuilder create(final int inputSize) {
        if (inputSize < 1)
            throw new IllegalArgumentException("NeuralNetwork must contain at least one input");

        return new NeuralNetworkBuilder(inputSize);
    }

    public NeuralNetworkBuilder addLayer(final int size, final Activation... eligibleActivations) {
        int inputs = !layers.isEmpty()
                ? layers.get(layers.size() - 1).size()
                : inputSize;
        layers.add(Layer.random(inputs, size, eligibleActivations));
        return this;
    }

    public NeuralNetwork build() {
        return new NeuralNetwork(inputSize, layers);
    }
}
