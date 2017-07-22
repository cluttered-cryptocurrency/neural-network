package com.cluttered.cryptocurrency.ann;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.Activation.P_RELU;
import static com.cluttered.cryptocurrency.ann.Layer.generate;
import static java.lang.Math.random;
import static mockit.Deencapsulation.getField;
import static mockit.Deencapsulation.setField;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class LayerTest {

    @Tested
    @SuppressWarnings("unused")
    private Layer layer;

    @Injectable
    @SuppressWarnings("unused")
    private List<Neuron> neurons;

    @Test
    public void testGenerate(@Mocked final Activation activation) {
        final int inputSize = 42;
        final int size = 64;

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.randomNeurons(inputSize, size, activation); times = 1; result = neurons;
        }};

        final Layer result = generate(inputSize, size, activation);

        final List<Neuron> resultNeurons = getField(result, "neurons");

        assertThat(resultNeurons).isEqualTo(neurons);
    }

    @Test
    public void testSize() {
        final int size = 128;

        new Expectations() {{
            neurons.size(); times = 1; result = size;
        }};

        final int result = layer.size();

        assertThat(result).isEqualTo(size);
    }

    @Test
    public void testFire(@Mocked final Neuron neuron) {
        final List<Neuron> neurons = Arrays.asList(neuron, neuron, neuron, neuron);
        setField(layer, "neurons", neurons);
        final double input = random();
        final List<Double> inputs = Arrays.asList(input, input, input);
        final double expected = random();

        new Expectations() {{
            neuron.fire(inputs); times = neurons.size(); result = expected;
        }};

        final List<Double> result = layer.fire(inputs);

        assertThat(result).containsOnly(expected);
    }

    @Test
    public void testMutate(@Mocked final Neuron neuron,
                           @Mocked final Neuron mutatedNeuron) {
        final double mutationRate = random();
        final List<Neuron> neurons = Arrays.asList(neuron, neuron, neuron, neuron, neuron);
        setField(layer, "neurons", neurons);

        new Expectations() {{
            neuron.mutate(mutationRate); times = neurons.size(); result = mutatedNeuron;
        }};

        final Layer result = layer.mutate(mutationRate);
        final List<Neuron> resultNeurons = getField(result, "neurons");

        assertThat(resultNeurons.size()).isEqualTo(neurons.size());
        assertThat(resultNeurons).containsOnly(mutatedNeuron);
    }

    @Test
    public void testCrossover(@Mocked final Neuron neuron,
                              @Mocked final Neuron mateNeuron,
                              @Mocked final Neuron crossoverNeuron) {
        final List<Neuron> neurons = Arrays.asList(neuron, neuron, neuron, neuron, neuron);
        setField(layer, "neurons", neurons);
        final Layer mateLayer = Layer.generate(3, 5, P_RELU);
        final List<Neuron> mateNeurons = Arrays.asList(mateNeuron, mateNeuron, mateNeuron, mateNeuron, mateNeuron);
        setField(mateLayer, "neurons", mateNeurons);

        new Expectations() {{
            neuron.crossover(mateNeuron); times = neurons.size(); result = crossoverNeuron;
        }};

        final Layer result = layer.crossover(mateLayer);
        final List<Neuron> resultNeurons = getField(result, "neurons");

        assertThat(resultNeurons.size()).isEqualTo(neurons.size());
        assertThat(resultNeurons).containsOnly(crossoverNeuron);
    }

}