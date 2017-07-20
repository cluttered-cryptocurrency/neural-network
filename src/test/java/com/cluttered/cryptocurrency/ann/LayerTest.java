package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import com.cluttered.cryptocurrency.ann.layer.Layer;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static com.cluttered.cryptocurrency.ann.activation.Activation.TAN_H;
import static mockit.Deencapsulation.setField;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class LayerTest {

    @Tested
    @SuppressWarnings("unused")
    private Layer layer;

    @Injectable
    @SuppressWarnings("unused")
    private Set<Activation> eligibleActivations;

    @Injectable
    @SuppressWarnings("unused")
    private List<Neuron> neurons;

    @Test
    public void testRandom() {
        final int inputSize = 42;
        final int size = 16;
        final Set<Activation> eligibleActivations = new HashSet<>();
        eligibleActivations.add(TAN_H);

        final Layer result = Layer.random(inputSize, size, eligibleActivations);

        assertThat(result).isNotNull();
    }

    @Test
    public void testFire() {
        final List<Double> inputs = Arrays.asList(1.0, 2.0, 3.0);
        final Neuron neuron = Neuron.random(inputs.size(), Collections.emptySet());
        final List<Neuron> neurons = Arrays.asList(neuron, neuron, neuron);
        setField(layer, "neurons", neurons);

        final List<Double> result = layer.fire(inputs);

        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void testSize() {
        final int size = 42;

        new Expectations() {{
            neurons.size(); times = 1; result = size;
        }};

        final int result = layer.size();

        assertThat(result).isEqualTo(size);
    }
}