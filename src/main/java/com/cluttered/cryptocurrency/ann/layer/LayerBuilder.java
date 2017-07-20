package com.cluttered.cryptocurrency.ann.layer;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LayerBuilder {

    private Set<Activation> eligibleActivations;
    private List<Neuron> neurons = Collections.emptyList();

    private LayerBuilder() {
    }

    public static Layer random(final int inputSize, final int size, final Set<Activation> eligibleActivations) {
        if (inputSize < 1)
            throw new IllegalArgumentException("inputSize must be greater than 0");
        if (size < 1)
            throw new IllegalArgumentException("size must be greater than 0");

        return new LayerBuilder()
                .eligibleActivations(eligibleActivations)
                .randomNeurons(inputSize, size, eligibleActivations)
                .build();
    }

    private LayerBuilder eligibleActivations(final Set<Activation> eligibleActivations) {
        this.eligibleActivations = eligibleActivations;
        return this;
    }

    private LayerBuilder randomNeurons(final int inputSize, final int size, final Set<Activation> eligibleActivations) {
        neurons = IntStream.range(0, size)
                .mapToObj(i -> Neuron.random(inputSize, eligibleActivations))
                .collect(Collectors.toList());
        return this;
    }

    private Layer build() {
        return new Layer(eligibleActivations, neurons);
    }
}