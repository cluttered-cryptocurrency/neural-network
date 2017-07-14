package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;

import java.util.Collections;
import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public interface Neuron {

    /**
     * Fire this {@code Neuron} with the specified {@code input}. The output of this {@code Neuron} is returned.
     *
     * @param input The input used to calculate the {@code Neuron} object's output.
     * @return The output of this {@code Neuron}.
     */
    default BigFloat fire(final BigFloat input) {
        return fire(Collections.singletonList(input));
    }

    /**
     * Fire with {@code Neuron} with the specified {@code input}. The output of this {@code Neuron} is returned.
     *
     * @param inputs The {@code list} of {@code BigFloat} objects used to calculate the {@code Neuron} object's output.
     * @return The output of this {@code Neuron}.
     */
    BigFloat fire(final List<BigFloat> inputs);
}