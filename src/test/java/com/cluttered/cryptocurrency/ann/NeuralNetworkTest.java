package com.cluttered.cryptocurrency.ann;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static mockit.Deencapsulation.setField;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class NeuralNetworkTest {

    @Tested
    @SuppressWarnings("unused")
    private NeuralNetwork neuralNetwork;

    @Injectable
    @SuppressWarnings("unused")
    private final Integer inputSize = 1;

    @Injectable
    @SuppressWarnings("unused")
    private List<Layer> layers;

    @Test
    public void testFire(@Mocked Layer layer,
                         @Mocked List<Double> expected1,
                         @Mocked List<Double> expected2,
                         @Mocked List<Double> expected3) {

        final List<Double> inputs = Collections.singletonList(42.0);
        final List<Layer> layers = Arrays.asList(layer, layer, layer);
        setField(neuralNetwork, "layers", layers);

        new Expectations() {{
            layer.fire(inputs); times = 1; result = expected1;
            layer.fire(expected1); times = 1; result = expected2;
            layer.fire(expected2); times = 1; result = expected3;
        }};

        final List<Double> result = neuralNetwork.fire(inputs);

        assertThat(result).isEqualTo(expected3);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFire_WrongSize(@Mocked final List<Double> inputs) {
        neuralNetwork.fire(inputs);
    }
}