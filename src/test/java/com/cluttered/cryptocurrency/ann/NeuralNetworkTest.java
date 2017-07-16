package com.cluttered.cryptocurrency.ann;

import ch.obermuhlner.math.big.BigFloat;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
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
    private Long inputSize;

    @Injectable
    @SuppressWarnings("unused")
    private List<Layer> layers;

    @Test @Ignore
    public void testFire(@Mocked List<BigDecimal> inputs,
                         @Mocked Layer layer,
                         @Mocked BigDecimal expected1,
                         @Mocked BigDecimal expected2,
                         @Mocked BigDecimal expected3) {
        final List<Layer> layers = Arrays.asList(layer, layer, layer);
        setField(neuralNetwork, "layers", layers);
        final List<BigDecimal> expectedList1 = Collections.singletonList(expected1);
        final List<BigDecimal> expectedList2 = Collections.singletonList(expected2);
        final List<BigDecimal> expectedList3 = Collections.singletonList(expected3);

        new Expectations() {{
            layer.fire(inputs); times = 1; result = expected1;
            layer.fire(expectedList1); times = 1; result = expected2;
            layer.fire(expectedList2); times = 1; result = expected3;
        }};

        final List<BigDecimal> result = neuralNetwork.fire(inputs);

        assertThat(result).isEqualTo(expectedList3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFire_WrongSize(@Mocked final List<BigDecimal> inputs) {
        final Long inputSize = 32L;
        setField(neuralNetwork, "inputSize", inputSize);

        neuralNetwork.fire(inputs);
    }
}