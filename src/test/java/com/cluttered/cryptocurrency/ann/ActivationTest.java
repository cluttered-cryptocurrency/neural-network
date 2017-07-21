package com.cluttered.cryptocurrency.ann;

import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cluttered.cryptocurrency.ann.Activation.*;
import static java.lang.Math.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class ActivationTest {

    @Test
    public void testLinear() {
        final double input = random();
        final double result = LINEAR.execute(input, Double.NaN);
        assertThat(result).isEqualTo(input);
    }

    @Test
    public void testBinary() {
        final double negativeInput = random() * -100;
        final double negativeResult = Activation.BINARY.execute(negativeInput, Double.NaN);
        assertThat(negativeResult).isEqualTo(0);

        final double positiveInput = random() * 100;
        final double positiveResult = BINARY.execute(positiveInput, Double.NaN);
        assertThat(positiveResult).isEqualTo(1);

        final double zeroInput = 0;
        final double zeroResult = BINARY.execute(zeroInput, Double.NaN);
        assertThat(zeroResult).isEqualTo(0);
    }

    @Test
    public void testSigmoid() {
        final double input = random();
        final double expected = 1 / (1 + exp(-input));
        final double result = SIGMOID.execute(input, Double.NaN);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testHyperbolicTangent() {
        final double input = random();
        final double expected = tanh(input);
        final double result = TAN_H.execute(input, Double.NaN);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testInverseTangent() {
        final double input = random();
        final double expected = atan(input);
        final double result = A_TAN.execute(input, Double.NaN);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testRectifiedLinearUnit() {
        final double positiveInput = random() * 100;
        final double positiveResult = RELU.execute(positiveInput, Double.NaN);
        assertThat(positiveResult).isEqualTo(positiveInput);

        final double negativeInput = random() * -100;
        final double negativeResult = RELU.execute(negativeInput, Double.NaN);
        assertThat(negativeResult).isEqualTo(0);

        final double zeroInput = 0;
        final double zeroResult = RELU.execute(zeroInput, Double.NaN);
        assertThat(zeroResult).isEqualTo(0);
    }

    @Test
    public void testLeakyRectifiedLinearUnit() {
        final double positiveInput = random() * 100;
        final double positiveResult = L_RELU.execute(positiveInput, Double.NaN);
        assertThat(positiveResult).isEqualTo(positiveInput);

        final double negativeInput = random() * -100;
        final double negativeResult = L_RELU.execute(negativeInput, Double.NaN);
        assertThat(negativeResult).isEqualTo(0.01 * negativeInput);

        final double zeroInput = 0;
        final double zeroResult = L_RELU.execute(zeroInput, Double.NaN);
        assertThat(zeroResult).isEqualTo(0);
    }

    @Test
    public void testParametericRectifiedLinearUnit() {
        final double leakage = random();

        final double positiveInput = random() * 100;
        final double positiveResult = P_RELU.execute(positiveInput, leakage);
        assertThat(positiveResult).isEqualTo(positiveInput);

        final double negativeInput = random() * -100;
        final double negativeResult = P_RELU.execute(negativeInput, leakage);
        assertThat(negativeResult).isEqualTo(leakage * negativeInput);

        final double zeroInput = 0;
        final double zeroResult = P_RELU.execute(zeroInput, leakage);
        assertThat(zeroResult).isEqualTo(0);
    }

    @Test
    public void testExponentialLinearUnit() {
        final double leakage = random();

        final double positiveInput = random() * 100;
        final double positiveResult = ELU.execute(positiveInput, leakage);
        assertThat(positiveResult).isEqualTo(positiveInput);

        final double negativeInput = random() * -100;
        final double negativeExpected = leakage * (exp(negativeInput) - 1);
        final double negativeResult = ELU.execute(negativeInput, leakage);
        assertThat(negativeResult).isEqualTo(negativeExpected);

        final double zeroInput = 0;
        final double zeroResult = ELU.execute(zeroInput, leakage);
        assertThat(zeroResult).isEqualTo(0);
    }

    @Test
    public void testSoftPlus() {
        final double input = random();
        final double expected = log(1 + exp(input));
        final double result = SOFT_PLUS.execute(input, Double.NaN);
        assertThat(result).isEqualTo(expected);
    }
}