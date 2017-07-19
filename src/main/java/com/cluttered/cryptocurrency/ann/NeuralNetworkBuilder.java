package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ann.activation.Activation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuralNetworkBuilder {

    private int inputSize;
    private List<Layer> layers = new ArrayList<>();

    private NeuralNetworkBuilder(final int inputSize) {
        this.inputSize = inputSize;
    }

    public static NeuralNetworkBuilder create(final int inputSize) {
        return new NeuralNetworkBuilder(inputSize);
    }

    public NeuralNetworkBuilder addLayer(final int size, final Activation... eligibleActivations) {
        int inputs = !layers.isEmpty() ? layers.get(layers.size() - 1).size() : inputSize;
        return addLayer(Layer.random(inputs, size, eligibleActivations));
    }

    public NeuralNetworkBuilder addLayer(final Layer layer) {
        layers.add(layer);
        return this;
    }

    public NeuralNetwork addOutputLayer(final int size, final Activation... eligibleActivations) {
        addLayer(size, eligibleActivations);
        return build();
    }

    public NeuralNetwork addOutputLayer(final Layer layer) {
        layers.add(layer);
        return build();
    }

    public NeuralNetwork build() {
        if (inputSize < 1)
            throw new IllegalStateException("NeuralNetwork must contain at least one input");
        if (layers == null)
            layers = Collections.emptyList();
        return new NeuralNetwork(inputSize, layers);
    }
}
