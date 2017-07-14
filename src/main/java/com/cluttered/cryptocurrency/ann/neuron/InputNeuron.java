package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;

import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class InputNeuron implements Neuron {

    @Override
    public BigFloat fire(final List<BigFloat> inputs) {
        if(inputs.size() != 1)
            throw new IllegalArgumentException("InputNeuron accepts one, and only one input");
        return inputs.get(0);
    }
}