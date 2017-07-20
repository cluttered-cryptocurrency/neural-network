package com.cluttered.cryptocurrency.ann.neuron;

import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testRandom_InputSizeLessThan1() {
        NeuronBuilder.random(0, Collections.emptySet());
    }

    @Test
    public void testRandom_NullEligibleActivations() {
        final Neuron neuron = NeuronBuilder.random(32, null);
        assertThat(neuron).isNotNull();
    }
}