package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.cluttered.cryptocurrency.ann.activation.Activation.SIGMOID;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilderTest {

    @Test
    public void testRandom() {
        final Set<Activation> activations = new HashSet<>();
        activations.add(SIGMOID);
        final Neuron neuron = NeuronBuilder.random(32, activations);
        assertThat(neuron).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandom_InputSizeLessThan1() {
        NeuronBuilder.random(0, Collections.emptySet());
    }
}