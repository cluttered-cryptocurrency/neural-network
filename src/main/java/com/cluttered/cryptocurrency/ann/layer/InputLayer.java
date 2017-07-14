package com.cluttered.cryptocurrency.ann.layer;

import ch.obermuhlner.math.big.BigFloat;

import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public class InputLayer implements Layer {

    private final long size;

    private InputLayer(final long size) {
        this.size = size;
    }

    @Override
    public List<BigFloat> fire(final List<BigFloat> inputs) {
        if (inputs.size() != size)
            throw new IllegalArgumentException("InputLayer accepts " + size + " inputs but received " + inputs.size());
        return inputs;
    }
}