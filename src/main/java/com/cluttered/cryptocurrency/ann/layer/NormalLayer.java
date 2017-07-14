package com.cluttered.cryptocurrency.ann.layer;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cluttered.code@gmail.com
 */
public class NormalLayer implements Layer {

    private final List<Neuron> neurons;

    private NormalLayer(final List<Neuron> neurons) {
        this.neurons = neurons;
    }

    @Override
    public List<BigFloat> fire(final List<BigFloat> inputs) {
        // TODO: parallelize and profile speed
        return neurons.stream()
                .map(neuron -> neuron.fire(inputs))
                .collect(Collectors.toList());
    }
}