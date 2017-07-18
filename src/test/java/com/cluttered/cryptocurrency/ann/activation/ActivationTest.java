package com.cluttered.cryptocurrency.ann.activation;

import ch.obermuhlner.math.big.BigFloat;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static com.cluttered.cryptocurrency.ann.MathConstants.BIG_FLOAT_CONTEXT;
import static com.cluttered.cryptocurrency.ann.MathConstants.PRECISION;
import static com.cluttered.cryptocurrency.ann.activation.Activation.*;
import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class ActivationTest {

    @Test
    public void testLinearActivation() {
        final BigDecimal input = BigDecimal.ONE;
        final BigDecimal result = LINEAR.evaluate(input);
        assertThat(result).isEqualTo(input);
    }

    @Test
    public void testSigmoidActivation(@Mocked final BigFloat bigFloatNegative,
                                      @Mocked final BigFloat bigFloatExponent) {

        final BigDecimal input = BigDecimal.valueOf(1);
        final BigDecimal negative = BigDecimal.valueOf(2);
        final BigDecimal exponent = BigDecimal.valueOf(3);
        final BigDecimal addition = BigDecimal.valueOf(4);
        final BigDecimal expected = BigDecimal.valueOf(5);

        new Expectations(BIG_FLOAT_CONTEXT, BigDecimal.class) {{
            input.negate(); times = 1; result = negative;
            BIG_FLOAT_CONTEXT.valueOf(negative); times = 1; result = bigFloatNegative;
            BigFloat.exp(bigFloatNegative); times = 1; result = bigFloatExponent;
            bigFloatExponent.toBigDecimal(); times = 1; result = exponent;
            ONE.add(exponent); times = 1; result = addition;
            ONE.divide(addition, PRECISION, HALF_UP); times = 1; result = expected;
        }};

        final BigDecimal result = SIGMOID.evaluate(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testHyperbolicTangentActivation(@Mocked final BigFloat bigFloatInput,
                                                @Mocked final BigFloat hyperbolicTangent) {
        final BigDecimal input = BigDecimal.valueOf(1);
        final BigDecimal expected = BigDecimal.valueOf(2);

        new Expectations(BIG_FLOAT_CONTEXT) {{
            BIG_FLOAT_CONTEXT.valueOf(input); times = 1; result = bigFloatInput;
            BigFloat.tanh(bigFloatInput); times = 1; result = hyperbolicTangent;
            hyperbolicTangent.toBigDecimal(); times = 1; result = expected;
        }};

        final BigDecimal result = TAN_H.evaluate(input);
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

        final Activation activation =  random();
        assertThat(activation).isEqualTo(LINEAR);
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

        final Activation activation = random();
        assertThat(activation).isEqualTo(LINEAR);
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

        final Activation activation = random();
        assertThat(activation).isEqualTo(SIGMOID);
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

        final Activation activation = random();
        assertThat(activation).isEqualTo(SIGMOID);
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

        final Activation activation = random();
        assertThat(activation).isEqualTo(TAN_H);
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

        final Activation activation = random();
        assertThat(activation).isEqualTo(TAN_H);
    }
}