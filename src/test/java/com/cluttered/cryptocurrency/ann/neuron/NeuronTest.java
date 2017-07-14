package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class NeuronTest {

    private final Neuron neuron = NeuronBuilder.create().build();

    @Test
    public void testFire(@Mocked final BigFloat input,
                         @Mocked final BigFloat expected) {
        final List<BigFloat> inputs = Collections.singletonList(input);

        new Expectations(neuron) {{
            neuron.fire(inputs); times = 1; result = expected;
        }};

        final BigFloat result = neuron.fire(input);

        assertThat(result).isEqualTo(expected);
    }
}