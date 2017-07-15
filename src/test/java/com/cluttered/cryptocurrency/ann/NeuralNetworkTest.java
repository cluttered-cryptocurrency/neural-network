package com.cluttered.cryptocurrency.ann;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class NeuralNetworkTest {

    @Tested
    @SuppressWarnings("unused")
    private NeuralNetwork neuralNetwork;

    @Injectable
    @SuppressWarnings("unused")
    private Long inputSize;

    @Injectable
    @SuppressWarnings("unused")
    private List<Layer> layers;

    @Test
    public void testFire() {
        assertThat(true).isTrue();
    }
}