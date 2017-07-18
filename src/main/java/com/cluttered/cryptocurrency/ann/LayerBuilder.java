package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LayerBuilder {

    private List<Neuron> neurons = Collections.emptyList();

    private LayerBuilder() {
    }

    protected static LayerBuilder create() {
        return new LayerBuilder();
    }

    public LayerBuilder randomNeurons(final int inputSize, final int size, final Activation... eligibleActivations) {
        if(inputSize < 1)
            throw new IllegalArgumentException("inputSize must be greater than 0");
        if(size < 1)
            throw new IllegalArgumentException("size must be greater than 0");
        return neurons(IntStream.range(0, size)
                .mapToObj(i -> Neuron.random(inputSize, eligibleActivations))
                .collect(Collectors.toList()));
    }

    public LayerBuilder neurons(final Neuron... neurons) {
        return neurons(Arrays.asList(neurons));
    }

    public LayerBuilder neurons(final List<Neuron> neurons) {
        this.neurons = neurons;
        return this;
    }

    public Layer random(final int inputSize, final int size, final Activation... eligibleActivations) {
        randomNeurons(inputSize, size, eligibleActivations);
        return build();
    }

    public Layer build() {
        if(neurons == null || neurons.isEmpty())
            throw new IllegalStateException("Layer must contain at least one Neuron");
        return new Layer(neurons);
    }
}
