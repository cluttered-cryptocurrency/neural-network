package com.cluttered.cryptocurrency.ann;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.MathConstants.BIG_FLOAT_CONTEXT_100_HALF_UP;
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
    public void testFire() {
        final List<BigFloat> inputs = Arrays.asList(
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(42)
        );
        final List<Neuron> neurons = Arrays.asList(
                Neuron.builder().build(),
                Neuron.builder().weights(BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(0.5)).build(),
                Neuron.builder().bias(BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(30)).build(),
                Neuron.builder().activation(Activation.TAN_H).build()
        );
        setField(layer, "neurons", neurons);

        final List<BigFloat> result = layer.fire(inputs);

        assertThat(result).containsExactly(
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(42),
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(21.0),
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(12),
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf("0.9999999999999999999999999999999999993388598746478531403089807148529105038558706553783536798671429480")
        );
    }
}