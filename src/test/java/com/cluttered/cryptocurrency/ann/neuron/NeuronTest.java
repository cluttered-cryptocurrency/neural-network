package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;
import mockit.Deencapsulation;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.cluttered.cryptocurrency.ann.activation.Activation.LINEAR;
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
    private BigFloat bias;

    @Injectable
    @SuppressWarnings("unused")
    private List<BigFloat> weights;

    @Injectable
    @SuppressWarnings("unused")
    private final Activation activation = LINEAR;

    @Test
    public void testConstructor() {
        final BigFloat privateBias = Deencapsulation.getField(neuron, "bias");
        final List<BigFloat> privateWeights = Deencapsulation.getField(neuron, "weights");
        final Activation privateActivation = Deencapsulation.getField(neuron, "activation");

        assertThat(privateBias).isEqualTo(bias);
        assertThat(privateWeights).isEqualTo(weights);
        assertThat(privateActivation).isEqualTo(activation);
    }

}