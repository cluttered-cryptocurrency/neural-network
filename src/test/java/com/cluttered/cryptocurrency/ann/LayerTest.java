package com.cluttered.cryptocurrency.ann;

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
                         @Mocked final Neuron neuron) {

        final BigDecimal expected1 = BigDecimal.valueOf(1);
        final BigDecimal expected2 = BigDecimal.valueOf(2);
        final BigDecimal expected3 = BigDecimal.valueOf(3);

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