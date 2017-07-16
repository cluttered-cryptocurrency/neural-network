package com.cluttered.cryptocurrency.ann;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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
    private List<Neuron> neurons;

    @Test
    public void testFire(@Mocked final List<BigDecimal> inputs,
                         @Mocked final Neuron neuron,
                         @Mocked final BigDecimal expected1,
                         @Mocked final BigDecimal expected2,
                         @Mocked final BigDecimal expected3) {
        final List<Neuron> neurons = Arrays.asList(neuron, neuron, neuron);
        setField(layer, "neurons", neurons);

        new Expectations() {{
            neuron.fire(inputs); times = 3; returns(expected1, expected2, expected3);
        }};

        final List<BigDecimal> result = layer.fire(inputs);

        assertThat(result).containsExactly(
                expected1,
                expected2,
                expected3
        );
    }
}