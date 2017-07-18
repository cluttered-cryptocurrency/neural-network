package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import mockit.Deencapsulation;
import mockit.Mocked;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.activation.Activation.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilderTest {

    @Test
    public void testCreate() {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create();

        final Double privateBias = Deencapsulation.getField(neuronBuilder, "bias");
        final List<Double> privateWeights = Deencapsulation.getField(neuronBuilder, "weights");
        final Activation privateActivation = Deencapsulation.getField(neuronBuilder, "activation");

        assertThat(privateBias).isEqualTo(0.0);
        assertThat(privateWeights).isEqualTo(Collections.singletonList(1.0));
        assertThat(privateActivation).isEqualTo(LINEAR);
    }

    @Test
    public void testBias() {
        final double bias = 42.0;
        final NeuronBuilder neuronBuilder = NeuronBuilder.create().bias(bias);

        final Double privateBias = Deencapsulation.getField(neuronBuilder, "bias");

        assertThat(privateBias).isEqualTo(bias);
    }

    @Test
    public void testWeights() {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create().weights(1.0, 2.5, -0.45);

        final List<Double> privateWeights = Deencapsulation.getField(neuronBuilder, "weights");

        assertThat(privateWeights).containsExactly(1.0, 2.5, -0.45);
    }

    @Test
    public void testLinear() {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create().linear();

        final Activation privateActivation = Deencapsulation.getField(neuronBuilder, "activation");

        assertThat(privateActivation).isEqualTo(LINEAR);
    }

    @Test
    public void testSigmoid() {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create().sigmoid();

        final Activation privateActivation = Deencapsulation.getField(neuronBuilder, "activation");

        assertThat(privateActivation).isEqualTo(SIGMOID);
    }

    @Test
    public void testHyperbolicTangent() {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create().tanH();

        final Activation privateActivation = Deencapsulation.getField(neuronBuilder, "activation");

        assertThat(privateActivation).isEqualTo(TAN_H);
    }

    @Test
    public void testBuild(@Mocked final List<Double> weights,
                          @Mocked final Activation activation) {
        final double bias = 42.0;

        final Neuron neuron = NeuronBuilder.create()
                .bias(bias)
                .weights(weights)
                .activation(activation)
                .build();

        final Double privateBias = Deencapsulation.getField(neuron, "bias");
        final List<Double> privateWeights = Deencapsulation.getField(neuron, "weights");
        final Activation privateActivation = Deencapsulation.getField(neuron, "activation");

        assertThat(privateBias).isEqualTo(bias);
        assertThat(privateWeights).isEqualTo(weights);
        assertThat(privateActivation).isEqualTo(activation);
    }
}