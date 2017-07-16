package com.cluttered.cryptocurrency.ann.activation;

import java.math.BigDecimal;

/**
 * @author cluttered.code@gmail.com
 */
@FunctionalInterface
public interface ActivationFunction {
    BigDecimal evaluate(final BigDecimal input);
}