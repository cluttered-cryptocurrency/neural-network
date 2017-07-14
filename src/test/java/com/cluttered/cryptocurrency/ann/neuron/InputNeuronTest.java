package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class InputNeuronTest {

    @Tested
    @SuppressWarnings("unused")
    private InputNeuron inputNeuron;

    @Test
    public void testFire(@Mocked final BigFloat input) {
        final BigFloat result = inputNeuron.fire(input);
        assertThat(result).isEqualTo(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFire_ToManyElements(@Mocked final List<BigFloat> inputs) {
        new Expectations() {{
            inputs.size(); times = 1; result = 2;
        }};

        inputNeuron.fire(inputs);
    }
}