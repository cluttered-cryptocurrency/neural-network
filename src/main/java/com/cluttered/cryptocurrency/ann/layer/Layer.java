package com.cluttered.cryptocurrency.ann.layer;

import ch.obermuhlner.math.big.BigFloat;

import java.util.List;

/**
 * @author cluttered.code@gmail.com
 */
public interface Layer {

    List<BigFloat> fire(final List<BigFloat> inputs);
}