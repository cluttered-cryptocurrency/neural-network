package com.cluttered.cryptocurrency;

import ch.obermuhlner.math.big.BigFloat;

import java.math.MathContext;

import static java.math.RoundingMode.HALF_UP;

/**
 * @author cluttered.code@gmail.com
 */
public class BigFloatConstants {

    public static final MathContext MATH_CONTEXT_100_HALF_UP = new MathContext(100, HALF_UP);
    public static final BigFloat.Context BIG_FLOAT_CONTEXT_100_HALF_UP = BigFloat.context(MATH_CONTEXT_100_HALF_UP);

    public static final BigFloat ONE = BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(1);
}