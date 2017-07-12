package com.cluttered.cryptocurrency.ann.activation;

import ch.obermuhlner.math.big.BigFloat;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cluttered.cryptocurrency.BigFloatConstants.ONE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class ActivationTest {

    @Test
    public void testLinearActivation(@Mocked final BigFloat input) {
        final BigFloat result = Activation.LINEAR.evaluate(input);
        assertThat(result).isEqualTo(input);
    }

    @Test
    public void testSigmoidActivation(@Mocked final BigFloat input,
                                      @Mocked final BigFloat negative,
                                      @Mocked final BigFloat exponent,
                                      @Mocked final BigFloat addition,
                                      @Mocked final BigFloat expected) {
        // Initialize ONE before test (jmockit quirk)
        final BigFloat one = ONE;

        new Expectations() {{
            BigFloat.negate(input); times = 1; result = negative;
            BigFloat.exp(negative); times = 1; result = exponent;
            ONE.add(exponent); times = 1; result = addition;
            ONE.divide(addition); times = 1; result = expected;
        }};

        final BigFloat result = Activation.SIGMOID.evaluate(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testHyperbolicTangentActivation(@Mocked final BigFloat input,
                                                @Mocked final BigFloat expected) {
        new Expectations() {{
            BigFloat.tanh(input); times = 1; result = expected;
        }};

        final BigFloat result = Activation.TAN_H.evaluate(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testRandomLinearLow() {
        @SuppressWarnings("unused")
        MockUp<Math> mockUp = new MockUp<Math>() {
            @Mock
            @SuppressWarnings("unused")
            double random() {
                return 0.0;
            }
        };

        final Activation activation = Activation.random();
        assertThat(activation).isEqualTo(Activation.LINEAR);
    }

    @Test
    public void testRandomLinearHigh() {
        @SuppressWarnings("unused")
        MockUp<Math> mockUp = new MockUp<Math>() {
            @Mock
            @SuppressWarnings("unused")
            double random() {
                return 0.33;
            }
        };

        final Activation activation = Activation.random();
        assertThat(activation).isEqualTo(Activation.LINEAR);
    }

    @Test
    public void testRandomSigmoidLow() {
        @SuppressWarnings("unused")
        MockUp<Math> mockUp = new MockUp<Math>() {
            @Mock
            @SuppressWarnings("unused")
            double random() {
                return 0.34;
            }
        };

        final Activation activation = Activation.random();
        assertThat(activation).isEqualTo(Activation.SIGMOID);
    }

    @Test
    public void testRandomSigmoidHigh() {
        @SuppressWarnings("unused")
        MockUp<Math> mockUp = new MockUp<Math>() {
            @Mock
            @SuppressWarnings("unused")
            double random() {
                return 0.66;
            }
        };

        final Activation activation = Activation.random();
        assertThat(activation).isEqualTo(Activation.SIGMOID);
    }

    @Test
    public void testRandomHyperbolicTangentLow() {
        @SuppressWarnings("unused")
        MockUp<Math> mockUp = new MockUp<Math>() {
            @Mock
            @SuppressWarnings("unused")
            double random() {
                return 0.67;
            }
        };

        final Activation activation = Activation.random();
        assertThat(activation).isEqualTo(Activation.TAN_H);
    }

    @Test
    public void testRandomHyperbolicTangentHigh() {
        @SuppressWarnings("unused")
        MockUp<Math> mockUp = new MockUp<Math>() {
            @Mock
            @SuppressWarnings("unused")
            double random() {
                return 0.99;
            }
        };

        final Activation activation = Activation.random();
        assertThat(activation).isEqualTo(Activation.TAN_H);
    }
}