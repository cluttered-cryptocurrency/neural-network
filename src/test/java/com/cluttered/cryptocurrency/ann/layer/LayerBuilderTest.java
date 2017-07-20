package com.cluttered.cryptocurrency.ann.layer;

import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class LayerBuilderTest {

    @Test
    public void testRandom() {
        final int inputSize = 42;
        final int size = 32;

        final Layer layer = LayerBuilder.random(inputSize, size, Collections.emptySet());

        assertThat(layer).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandom_InputSizeLessThan1() {
        LayerBuilder.random(0, 32, Collections.emptySet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandom_SizeLessThan1() {
        LayerBuilder.random(32, 0, Collections.emptySet());
    }
}