package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ann.neuron.Neuron;

import java.math.BigDecimal;
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

    public List<BigDecimal> fire(final List<BigDecimal> inputs) {
        // TODO: parallelize and profile speed
        // Stream was untestable by JMockit
        final List<BigDecimal> results = new ArrayList<>(neurons.size());
        for (final Neuron neuron : neurons) {
            results.add(neuron.fire(inputs));
        }
        return results;
    }
}