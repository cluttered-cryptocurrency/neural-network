package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.MathContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.activation.Activation.LINEAR;
import static java.math.RoundingMode.HALF_UP;
import static mockit.Deencapsulation.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class NormalNeuronTest {

    @Tested
    @SuppressWarnings("unused")
    private NormalNeuron neuron;

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
        final BigFloat privateBias = getField(neuron, "bias");
        final List<BigFloat> privateWeights = getField(neuron, "weights");
        final Activation privateActivation = getField(neuron, "activation");

        assertThat(privateBias).isEqualTo(bias);
        assertThat(privateWeights).isEqualTo(weights);
        assertThat(privateActivation).isEqualTo(activation);
    }

    @Test
    public void testBuilder(@Mocked final NeuronBuilder neuronBuilder) {
        new Expectations() {{
            NeuronBuilder.create(); times = 1; result = neuronBuilder;
        }};

        final NeuronBuilder result = NormalNeuron.builder();

        assertThat(result).isEqualTo(neuronBuilder);
    }

    @Test
    public void testFire(@Mocked final List<BigFloat> inputs,
                         @Mocked final BigFloat dotProduct,
                         @Mocked final BigFloat subtraction,
                         @Mocked final BigFloat expected) {

        new MockUp<NormalNeuron>() {
            @Mock(invocations = 1)
            @SuppressWarnings("unused")
            private BigFloat dotProductWithWeights(final List<BigFloat> mockInputs) {
                assertThat(mockInputs).isEqualTo(inputs);
                return dotProduct;
            }
        };

        new Expectations() {{
            dotProduct.subtract(bias); times = 1; result = subtraction;
            activation.evaluate(subtraction); times = 1; result = expected;
        }};

        final BigFloat result = neuron.fire(inputs);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testDotProductWithWeights() {
        // initialize for jmockit quirk
        MathContext MATH_CONTEXT_100_HALF_UP = new MathContext(100, HALF_UP);
        BigFloat.Context BIG_FLOAT_CONTEXT_100_HALF_UP = BigFloat.context(MATH_CONTEXT_100_HALF_UP);

        final List<BigFloat> weights = Arrays.asList(
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(95.48294),
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(34.68492),
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(63.56372)
        );
        setField(neuron, "weights", weights);

        final List<BigFloat> inputs = Arrays.asList(
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(22.34567),
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(870.39846),
                BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(1.00034)
        );
        final BigFloat expected = BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(32386.9165527578);

        final BigFloat result = invoke(neuron, "dotProductWithWeights", inputs);

        assertThat(result).isEqualByComparingTo(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWithWeights_SizeDiff(@Mocked final BigFloat bigFloat) {
        final List<BigFloat> weights = Collections.singletonList(bigFloat);
        final Neuron neuron = NormalNeuron.builder().weights(weights).build();

        final List<BigFloat> inputs = Arrays.asList(bigFloat, bigFloat, bigFloat);
        invoke(neuron, "dotProductWithWeights", inputs);
    }
}