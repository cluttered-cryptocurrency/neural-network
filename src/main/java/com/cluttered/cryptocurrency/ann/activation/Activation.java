package com.cluttered.cryptocurrency.ann.activation;

import ch.obermuhlner.math.big.BigFloat;

import java.math.BigDecimal;

import static com.cluttered.cryptocurrency.ann.MathConstants.BIG_FLOAT_CONTEXT;
import static com.cluttered.cryptocurrency.ann.MathConstants.PRECISION;
import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;

/**
 * @author cluttered.code@gmail.com
 */
public enum Activation implements ActivationFunction {
    LINEAR(input -> input),
    SIGMOID(input -> { // 1 / (1 + exp(-input))
        final BigDecimal exp = BigFloat.exp(BIG_FLOAT_CONTEXT.valueOf(input.negate())).toBigDecimal();
        return ONE.divide(ONE.add(exp), PRECISION, HALF_UP);
    }),
    TAN_H(input -> BigFloat.tanh(BIG_FLOAT_CONTEXT.valueOf(input)).toBigDecimal());

    private final ActivationFunction activationFunction;

    Activation(final ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public BigDecimal evaluate(final BigDecimal input) {
        return activationFunction.evaluate(input);
    }

    public static Activation random() {
        return values()[(int) (Math.random() * values().length)];
    }
}