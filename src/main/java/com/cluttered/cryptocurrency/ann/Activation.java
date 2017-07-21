package com.cluttered.cryptocurrency.ann;

import static java.lang.Math.*;

/**
 * @author cluttered.code@gmail.com
 */
public enum Activation {
    LINEAR((input, leakage) -> input),
    BINARY((input, leakage) -> input > 0 ? 1 : 0),
    SIGMOID((input, leakage) -> 1 / (1 + exp(-input))),
    TAN_H((input, leakage) -> tanh(input)),
    A_TAN((input, leakage) -> atan(input)),
    RELU((input, leakage) -> max(0, input)),
    L_RELU((input, leakage) -> input < 0 ? 0.01 * input : input),
    P_RELU((input, leakage) -> input < 0 ? leakage * input : input),
    ELU((input, leakage) -> input < 0 ? leakage * (exp(input) - 1) : input),
    SOFT_PLUS((input, leakage) -> log(1 + exp(input)));

    private final ActivationFunction activationFunction;

    Activation(final ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public double execute(final double input, final double leakage) {
        return activationFunction.execute(input, leakage);
    }

    @FunctionalInterface
    public interface ActivationFunction {
        double execute(final double input, final double leakage);
    }
}