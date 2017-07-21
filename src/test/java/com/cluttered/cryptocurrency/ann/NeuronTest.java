package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.RandomGenerator;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.cluttered.cryptocurrency.RandomGenerator.randomBias;
import static com.cluttered.cryptocurrency.RandomGenerator.randomLeakage;
import static com.cluttered.cryptocurrency.RandomGenerator.randomWeights;
import static mockit.Deencapsulation.getField;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JMockit.class)
public class NeuronTest {

    @Test
    public void testGenerate(@Mocked final List<Double> weights) {
        final int inputSize = 42;
        final double bias = 0.347563;
        final double leakage = 0.00234;

        new Expectations(RandomGenerator.class) {{
            randomBias(); times = 1; result = bias;
            randomLeakage(); times = 1; result = leakage;
            randomWeights(inputSize); times = 1; result = weights;
        }};

        final Neuron result = Neuron.generate(inputSize);

        assertThat((Double) getField(result, "bias")).isEqualTo(bias);
        assertThat((Double) getField(result, "leakage")).isEqualTo(leakage);
    }

}