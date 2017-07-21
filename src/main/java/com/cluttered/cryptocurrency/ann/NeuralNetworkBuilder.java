package com.cluttered.cryptocurrency.ann;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuralNetworkBuilder {

    private int inputSize;
    private ActivationFunction activation;
    private List<Layer> layers = new ArrayList<>();

    private NeuralNetworkBuilder(final int inputSize, final ActivationFunction activation) {
        this.inputSize = inputSize;
        this.activation = activation;
    }

    protected static NeuralNetworkBuilder create(final int inputSize, final ActivationFunction activation) {
        if (inputSize < 1)
            throw new IllegalArgumentException("NeuralNetwork must contain at least one input");

        return new NeuralNetworkBuilder(inputSize, activation);
    }

    public NeuralNetworkBuilder addLayer(final int size) {
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
