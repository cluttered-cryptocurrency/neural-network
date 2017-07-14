package com.cluttered.cryptocurrency.ann.activation;

import ch.obermuhlner.math.big.BigFloat;

import static com.cluttered.cryptocurrency.ann.MathConstants.ONE;

/**
 * @author cluttered.code@gmail.com
 */
public enum Activation implements ActivationFunction {
    LINEAR(input -> input),
    SIGMOID(input -> ONE.divide(ONE.add(BigFloat.exp(BigFloat.negate(input))))), // 1/(1+exp(-input)))
    TAN_H(BigFloat::tanh);

    private final ActivationFunction activationFunction;

    Activation(final ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public BigFloat evaluate(final BigFloat input) {
        return activationFunction.evaluate(input);
    }

    public static Activation random() {
        return values()[(int) (Math.random() * values().length)];
    }
}