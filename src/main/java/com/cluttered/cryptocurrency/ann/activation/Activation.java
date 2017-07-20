package com.cluttered.cryptocurrency.ann.activation;

/**
 * @author cluttered.code@gmail.com
 */
public enum Activation implements ActivationFunction {
    LINEAR(input -> input),
    SIGMOID(input -> 1 / (1 + Math.exp(-input))),
    TAN_H(Math::tanh);

    private final ActivationFunction activationFunction;

    Activation(final ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public Double evaluate(final Double input) {
        return activationFunction.evaluate(input);
    }
}