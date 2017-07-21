package com.cluttered.cryptocurrency.ann;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class Builder {

    private int inputSize;
    private Activation activation;
    private List<Layer> layers = new ArrayList<>();

    private Builder(final int inputSize, final Activation activation) {
        this.inputSize = inputSize;
        this.activation = activation;
    }

    protected static Builder create(final int inputSize, final Activation activation) {
        if (inputSize < 1)
            throw new IllegalArgumentException("NeuralNetwork must contain at least one input");

        return new Builder(inputSize, activation);
    }

    public Builder addLayer(final int size) {
        int inputs = !layers.isEmpty()
                ? layers.get(layers.size() - 1).size()
                : inputSize;

        layers.add(Layer.generate(inputs, size, activation));
        return this;
    }

    public NeuralNetwork build() {
        return new NeuralNetwork(inputSize, layers);
    }
}
