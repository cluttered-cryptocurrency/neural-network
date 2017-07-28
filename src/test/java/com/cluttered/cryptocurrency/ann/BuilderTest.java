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

import static com.cluttered.cryptocurrency.ann.Activation.A_TAN;
import static com.cluttered.cryptocurrency.ann.Activation.SIGMOID;
import static mockit.Deencapsulation.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author david.clutter@gmail.com
 */
@RunWith(JMockit.class)
public class BuilderTest {

    @Tested
    @SuppressWarnings("unused")
    private Builder builder;

    @Injectable
    @SuppressWarnings("unused")
    private final int inputSize = 12;

    @Injectable
    @SuppressWarnings("unused")
    private final Activation activation = SIGMOID;

    @Test
    public void testCreate(@Mocked final Activation activation) {
        final int inputSize = 200;

        final Builder result = Builder.create(200, activation);

        final int resultInputSize = getField(result, "inputSize");
        final Activation resultActivation = getField(result, "activation");

        assertThat(resultInputSize).isEqualTo(inputSize);
        assertThat(resultActivation).isEqualTo(activation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate_NoInputSize() {
        Builder.create(0, A_TAN);
    }

    @Test
    public void testAddLayers(@Mocked final Neuron neuron) {
        final List<Neuron> neurons1 = Arrays.asList(neuron, neuron, neuron, neuron);
        final Layer layer1 = newInstance(Layer.class, neurons1);
        final Layer layer2 = newInstance(Layer.class, Arrays.asList(neuron));

        new Expectations(Layer.class) {{
            Layer.generate(inputSize,4, activation); times = 1; result = layer1;
            Layer.generate(layer1.size(), 1, activation); times = 1; result = layer2;
        }};

        builder.addLayer(4);
        builder.addLayer(1);

        final List<Layer> result = getField(builder, "layers");

        assertThat(result).containsOnly(layer1, layer2);
    }

    @Test
    public void testBuild(@Mocked final List<Layer> layers) {
        setField(builder, "layers", layers);

        final NeuralNetwork result = builder.build();

        final int resultInputSize = getField(result, "inputSize");
        final List<Layer> resultLayers = getField(result, "layers");

        assertThat(resultInputSize).isEqualTo(inputSize);
        assertThat(resultLayers).isEqualTo(layers);
    }
}