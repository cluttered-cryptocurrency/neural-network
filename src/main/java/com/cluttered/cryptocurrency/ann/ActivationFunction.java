package com.cluttered.cryptocurrency.ann;

/**
 * @author cluttered.code@gmail.com
 */
@FunctionalInterface
public interface ActivationFunction {
    double execute(final double input, final double leakage);
}
