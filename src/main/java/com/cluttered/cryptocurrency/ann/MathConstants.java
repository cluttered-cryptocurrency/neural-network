package com.cluttered.cryptocurrency.ann;

import ch.obermuhlner.math.big.BigFloat;

/**
 * @author cluttered.code@gmail.com
 */
public interface MathConstants {

    int PRECISION = 100;
    BigFloat.Context BIG_FLOAT_CONTEXT = BigFloat.context(PRECISION);
}