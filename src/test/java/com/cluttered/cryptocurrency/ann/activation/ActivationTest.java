package com.cluttered.cryptocurrency.ann.activation;

import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cluttered.cryptocurrency.ann.activation.Activation.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class ActivationTest {

    @Test
    public void testLinearActivation() {
        final double input = 42;

        final double result = LINEAR.evaluate(input);

        assertThat(result).isEqualTo(input);
    }

    @Test
    public void testSigmoidActivation() {
        final double input = 1.23456789;
        final double expected = 0.7746170617399427;

        final double result = SIGMOID.evaluate(input);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testHyperbolicTangentActivation() {
        final double input = 1.23456789;

        final double result = TAN_H.evaluate(input);

        assertThat(result).isEqualTo(Math.tanh(input));
    }
}