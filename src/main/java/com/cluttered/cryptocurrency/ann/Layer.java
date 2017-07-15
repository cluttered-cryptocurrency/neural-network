package com.cluttered.cryptocurrency.ann;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class Layer {

    private final List<Neuron> neurons;

    public Layer(final List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public List<BigFloat> fire(final List<BigFloat> inputs) {
        // TODO: parallelize and profile speed
        final List<BigFloat> results = new ArrayList<>(neurons.size());
        for(final Neuron neuron : neurons) {
            results.add(neuron.fire(inputs));
        }
        return results;
    }
}