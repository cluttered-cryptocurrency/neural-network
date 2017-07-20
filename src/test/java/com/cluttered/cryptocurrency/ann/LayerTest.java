package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import com.cluttered.cryptocurrency.ann.layer.Layer;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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
    private final Activation[] eligibleActivations = Activation.values();

    @Injectable
    @SuppressWarnings("unused")
    private List<Neuron> neurons;

    @Test
    public void testFire() {
//        final List<Double> inputs = Arrays.asList(1.0, 2.0, 3.0);
//        final Neuron neuron1 = Neuron.builder().weights(1.0, 1.0, 1.0).build();
//        final Neuron neuron2 = Neuron.builder().weights(1.0, 1.0, 1.0).sigmoid().build();
//        final Neuron neuron3 = Neuron.builder().weights(1.0, 1.0, 1.0).tanH().build();
//        final List<Neuron> neurons = Arrays.asList(neuron1, neuron2, neuron3);
//        setField(layer, "neurons", neurons);
//
//        final List<Double> result = layer.fire(inputs);
//
//        assertThat(result).containsExactly(
//                6.0,
//                0.9975273768433653,
//                0.9999877116507956
//        );

    }
}