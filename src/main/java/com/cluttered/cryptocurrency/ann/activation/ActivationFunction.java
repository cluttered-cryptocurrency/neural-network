package com.cluttered.cryptocurrency.ann.activation;

import ch.obermuhlner.math.big.BigFloat;

/**
 * @author cluttered.code@gmail.com
 */
@FunctionalInterface
public interface ActivationFunction {
    BigFloat evaluate(final BigFloat input);
}