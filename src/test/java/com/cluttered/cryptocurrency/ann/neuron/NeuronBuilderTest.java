package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;
import mockit.Deencapsulation;
import mockit.Mocked;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.MathConstants.ONE;
import static com.cluttered.cryptocurrency.ann.activation.Activation.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
public class NeuronBuilderTest {

    @Test
    public void testCreate() {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create();

        final BigFloat privateBias = Deencapsulation.getField(neuronBuilder, "bias");
        final List<BigFloat> privateWeights = Deencapsulation.getField(neuronBuilder, "weights");
        final Activation privateActivation = Deencapsulation.getField(neuronBuilder, "activation");

        assertThat(privateBias).isEqualTo(ONE);
        assertThat(privateWeights).isEqualTo(Collections.singletonList(ONE));
        assertThat(privateActivation).isEqualTo(LINEAR);
    }

    @Test
    public void testBias(@Mocked final BigFloat bias) {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create().bias(bias);

        final BigFloat privateBias = Deencapsulation.getField(neuronBuilder, "bias");

        assertThat(privateBias).isEqualTo(bias);
    }

    @Test
    public void testWeights(@Mocked final BigFloat weight1,
                            @Mocked final BigFloat weight2,
                            @Mocked final BigFloat weight3) {
        final NeuronBuilder neuronBuilder = NeuronBuilder.create().weights(weight1, weight2, weight3);

        final List<BigFloat> privateWeights = Deencapsulation.getField(neuronBuilder, "weights");

        assertThat(privateWeights).containsExactly(weight1, weight2, weight3);
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
    public void testBuild(@Mocked final BigFloat bias,
                          @Mocked final List<BigFloat> weights,
                          @Mocked final Activation activation) {
        final Neuron neuron = NeuronBuilder.create()
                .bias(bias)
                .weights(weights)
                .activation(activation)
                .build();

        final BigFloat privateBias = Deencapsulation.getField(neuron, "bias");
        final List<BigFloat> privateWeights = Deencapsulation.getField(neuron, "weights");
        final Activation privateActivation = Deencapsulation.getField(neuron, "activation");

        assertThat(privateBias).isEqualTo(bias);
        assertThat(privateWeights).isEqualTo(weights);
        assertThat(privateActivation).isEqualTo(activation);
    }
}