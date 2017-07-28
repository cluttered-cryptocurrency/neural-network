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

import static com.cluttered.cryptocurrency.ann.Activation.BINARY;
import static com.cluttered.cryptocurrency.ann.Activation.ELU;
import static com.cluttered.cryptocurrency.ann.Activation.LINEAR;
import static java.lang.Math.random;
import static mockit.Deencapsulation.getField;
import static mockit.Deencapsulation.setField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

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
    private final Activation activation = LINEAR;

    @Injectable
    @SuppressWarnings("unused")
    private List<Double> weights;

    @Test
    public void testGenerate(@Mocked final Activation activation,
                             @Mocked final List<Double> weights) {
        final int inputSize = 32;

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.randomBias(); times = 1; result = bias;
            RandomGenerator.randomLeakage(); times = 1; result = leakage;
            RandomGenerator.randomWeights(inputSize); times = 1; result = weights;
        }};

        final Neuron result = Neuron.generate(32, activation);

        final double biasResult = getField(result, "bias");
        final double leakageResult = getField(result, "leakage");
        final Activation activationResult = getField(result, "activation");
        final List<Double> weightsResult = getField(result, "weights");

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

        new Expectations(neuron, activation) {{
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

        assertThat(result).isCloseTo(expected, within(0.01));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWithWeights_SizeDifference() {
        final List<Double> inputs = Arrays.asList(random());
        final List<Double> weights = Arrays.asList(random(), random());
        setField(neuron, "weights", weights);

        neuron.dotProductWithWeights(inputs);
    }

    @Test
    public void testMutate_All() {
        final double mutationRate = 1;
        final double weight = random();
        final List<Double> weights = Arrays.asList(weight, weight, weight, weight);
        setField(neuron, "weights", weights);

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.randomBias(); times = 1; result = bias;
            RandomGenerator.randomLeakage(); times = 1; result = leakage;
            RandomGenerator.randomWeight(); times = weights.size(); result = weight;
        }};

        final Neuron result = neuron.mutate(mutationRate);

        final double biasResult = getField(result, "bias");
        final double leakageResult = getField(result, "leakage");
        final Activation activationResult = getField(result, "activation");
        final List<Double> weightsResult = getField(result, "weights");

        assertThat(biasResult).isEqualTo(bias);
        assertThat(leakageResult).isEqualTo(leakage);
        assertThat(activationResult).isEqualTo(activation);
        assertThat(weightsResult).isEqualTo(weights);
    }

    @Test
    public void testMutate_None() {
        final double mutationRate = -1;
        final double weight = random();
        final List<Double> weights = Arrays.asList(weight, weight, weight, weight);
        setField(neuron, "weights", weights);

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.randomBias(); times = 0;
            RandomGenerator.randomLeakage(); times = 0;
            RandomGenerator.randomWeight(); times = 0;
        }};

        final Neuron result = neuron.mutate(mutationRate);

        final double biasResult = getField(result, "bias");
        final double leakageResult = getField(result, "leakage");
        final Activation activationResult = getField(result, "activation");
        final List<Double> weightsResult = getField(result, "weights");

        assertThat(biasResult).isEqualTo(bias);
        assertThat(leakageResult).isEqualTo(leakage);
        assertThat(activationResult).isEqualTo(activation);
        assertThat(weightsResult).isEqualTo(weights);
    }

    @Test
    public void testCrossover_Mine() {
        final List<Double> weights = Arrays.asList(random(), random(), random(), random());
        setField(neuron, "weights", weights);
        final Neuron mate = Neuron.generate(weights.size(), ELU);

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.coinFlip(); result = false;
        }};

        final Neuron result = neuron.crossover(mate);

        final double biasResult = getField(result, "bias");
        final double leakageResult = getField(result, "leakage");
        final Activation activationResult = getField(result, "activation");
        final List<Double> weightsResult = getField(result, "weights");

        assertThat(biasResult).isEqualTo(bias);
        assertThat(leakageResult).isEqualTo(leakage);
        assertThat(activationResult).isEqualTo(activation);
        assertThat(weightsResult).isEqualTo(weights);
    }

    @Test
    public void testCrossover_Mates() {
        setField(neuron, "activation", BINARY);
        final List<Double> weights = Arrays.asList(random(), random(), random(), random());
        setField(neuron, "weights", weights);
        final Neuron mate = Neuron.generate(weights.size(), ELU);

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.coinFlip(); result = true;
        }};

        final Neuron result = neuron.crossover(mate);

        final double biasResult = getField(result, "bias");
        final double leakageResult = getField(result, "leakage");
        final Activation activationResult = getField(result, "activation");
        final List<Double> weightsResult = getField(result, "weights");

        final double biasMate = getField(mate, "bias");
        final double leakageMate = getField(mate, "leakage");
        final Activation activationMate = getField(mate, "activation");
        final List<Double> weightsMate = getField(mate, "weights");

        assertThat(biasResult).isEqualTo(biasMate);
        assertThat(leakageResult).isEqualTo(leakageMate);
        assertThat(activationResult).isEqualTo(activationMate);
        assertThat(weightsResult).isEqualTo(weightsMate);
    }
}