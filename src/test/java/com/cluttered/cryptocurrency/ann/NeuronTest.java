package com.cluttered.cryptocurrency.ann;

import com.cluttered.cryptocurrency.RandomGenerator;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.random;
import static mockit.Deencapsulation.getField;
import static mockit.Deencapsulation.setField;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class NeuronTest {

    @Tested
    @SuppressWarnings("unused")
    private Neuron neuron;

    @Injectable
    @SuppressWarnings("unused")
    private final double bias = random();

    @Injectable
    @SuppressWarnings("unused")
    private final double leakage = random();

    @Injectable
    @SuppressWarnings("unused")
    private Activation activation;

    @Injectable
    @SuppressWarnings("unused")
    private List<Double> weights;

    @Test
    public void testGenerate(@Mocked final Activation activation,
                             @Mocked final List<Double> weights) {
        final int inputSize = 32;
        final double bias = random();
        final double leakage = random();

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.randomBias(); times = 1; result = bias;
            RandomGenerator.randomLeakage(); times = 1; result = leakage;
            RandomGenerator.randomWeights(inputSize); times = 1; result = weights;
        }};

        final Neuron neuron = Neuron.generate(32, activation);

        final double biasResult = getField(neuron, "bias");
        final double leakageResult = getField(neuron, "leakage");
        final Activation activationResult = getField(neuron, "activation");
        final List<Double> weightsResult = getField(neuron, "weights");

        assertThat(biasResult).isEqualTo(bias);
        assertThat(leakageResult).isEqualTo(leakage);
        assertThat(activationResult).isEqualTo(activation);
        assertThat(weightsResult).isEqualTo(weights);
    }

    @Test
    public void testFire(@Mocked final List<Double> inputs) {
        final double dotProduct = random();
        final double biasDotProduct = dotProduct + bias;
        final double activationResult = random();

        new Expectations(neuron) {{
            neuron.dotProductWithWeights(inputs); times = 1; result = dotProduct;
            activation.execute(biasDotProduct, leakage); times = 1; result = activationResult;
        }};

        final double result = neuron.fire(inputs);

        assertThat(result).isEqualTo(activationResult);
    }

    @Test
    public void testDotProductWithWeights() {
        final List<Double> inputs = Arrays.asList(random(), random(), random());
        final List<Double> weights = Arrays.asList(random(), random(), random());
        setField(neuron, "weights", weights);
        double expected = 0;
        for(int i = 0; i < inputs.size(); ++i) {
            expected += inputs.get(i) * weights.get(i);
        }

        final double result = neuron.dotProductWithWeights(inputs);

        assertThat(result).isEqualTo(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWithWeights_SizeDifference() {
        final List<Double> inputs = Arrays.asList(random());
        final List<Double> weights = Arrays.asList(random(), random());
        setField(neuron, "weights", weights);

        neuron.dotProductWithWeights(inputs);
    }
}