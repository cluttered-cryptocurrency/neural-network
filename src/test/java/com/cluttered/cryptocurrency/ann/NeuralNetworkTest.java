package com.cluttered.cryptocurrency.ann;

import com.google.gson.Gson;
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
import static mockit.Deencapsulation.*;
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
    private final int inputSize = 42;

    @Injectable
    @SuppressWarnings("unused")
    private List<Layer> layers;

    @Test
    public void testBuilder(@Mocked final Activation activation,
                            @Mocked final Builder builder) {
        final int inputSize = 5874;

        new Expectations() {{
            Builder.create(inputSize, activation); times = 1; result = builder;
        }};

        final Builder result = NeuralNetwork.builder(inputSize, activation);

        assertThat(result).isEqualTo(builder);
    }

    @Test
    public void testFromJson(@Mocked final String json) {
        final Gson gson = getField(NeuralNetwork.class, "GSON");

        new Expectations(gson) {{
            gson.fromJson(json, NeuralNetwork.class); times = 1; result = neuralNetwork;
        }};

        final NeuralNetwork result = NeuralNetwork.fromJson(json);

        assertThat(result).isEqualTo(neuralNetwork);
    }

    @Test
    public void testToJson(@Mocked final String json) {
        final Gson gson = getField(NeuralNetwork.class, "GSON");

        new Expectations(gson) {{
            gson.toJson(neuralNetwork); times = 1; result = json;
        }};

        final String result = neuralNetwork.toJson();

        assertThat(result).isEqualTo(json);
    }

    @Test
    public void testToString(@Mocked final String json) {
        new Expectations(neuralNetwork) {{
            neuralNetwork.toJson(); times = 1; result = json;
        }};

        final String result = neuralNetwork.toString();

        assertThat(result).isEqualTo(json);
    }

    @Test
    public void testFire(@Mocked final Layer layer1,
                         @Mocked final Layer layer2,
                         @Mocked final Layer layer3,
                         @Mocked final List<Double> layerResults1,
                         @Mocked final List<Double> layerResults2,
                         @Mocked final List<Double> layerResults3) {
        final List<Double> inputs = Arrays.asList(random(), random(), random(), random());
        setField(neuralNetwork, "inputSize", inputs.size());
        final List<Layer> layers = Arrays.asList(layer1, layer2, layer3);
        setField(neuralNetwork, "layers", layers);

        new Expectations() {{
            layer1.fire(inputs); times = 1; result = layerResults1;
            layer2.fire(layerResults1); times = 1; result = layerResults2;
            layer3.fire(layerResults2); times = 1; result = layerResults3;
        }};

        final List<Double> result = neuralNetwork.fire(inputs);

        assertThat(result).isEqualTo(layerResults3);
    }

    @Test
    public void testMutate(@Mocked final Layer layer,
                           @Mocked final Layer mutatedLayer) {
        final double mutationRate = random();
        final List<Layer> layers = Arrays.asList(layer, layer, layer, layer);
        setField(neuralNetwork, "layers", layers);

        new Expectations() {{
            layer.mutate(mutationRate); times = layers.size(); result = mutatedLayer;
        }};

        final NeuralNetwork result = neuralNetwork.mutate(mutationRate);
        final List<Layer> resultLayers = getField(result, "layers");

        assertThat(resultLayers.size()).isEqualTo(layers.size());
        assertThat(resultLayers).containsOnly(mutatedLayer);
    }

    @Test
    public void testCrossover(@Mocked final Layer layer,
                              @Mocked final Layer mateLayer,
                              @Mocked final Layer crossoverLayer) {
        final List<Layer> layers = Arrays.asList(layer, layer, layer);
        setField(neuralNetwork, "layers", layers);
        final List<Layer> mateLayers = Arrays.asList(mateLayer, mateLayer, mateLayer);
        final NeuralNetwork mateNeuralNetwork = newInstance(NeuralNetwork.class, 5, mateLayers);

        new Expectations() {{
            layer.crossover(mateLayer); times = layers.size(); result = crossoverLayer;
        }};

        final NeuralNetwork result = neuralNetwork.crossover(mateNeuralNetwork);
        final List<Layer> resultLayers = getField(result, "layers");

        assertThat(resultLayers.size()).isEqualTo(layers.size());
        assertThat(resultLayers).containsOnly(crossoverLayer);
    }
}