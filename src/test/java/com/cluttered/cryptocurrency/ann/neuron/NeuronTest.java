package com.cluttered.cryptocurrency.ann.neuron;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.activation.Activation;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
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
public class NeuronTest {

    @Tested
    @SuppressWarnings("unused")
    private Neuron neuron;

    @Injectable
    @SuppressWarnings("unused")
    private BigDecimal bias;

    @Injectable
    @SuppressWarnings("unused")
    private List<BigDecimal> weights;

    @Injectable
    @SuppressWarnings("unused")
    private final Activation activation = LINEAR;

    @Test
    public void testConstructor() {
        final BigDecimal privateBias = getField(neuron, "bias");
        final List<BigDecimal> privateWeights = getField(neuron, "weights");
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

        final NeuronBuilder result = Neuron.builder();

        assertThat(result).isEqualTo(neuronBuilder);
    }

    @Test @Ignore
    public void testFire(@Mocked final List<BigDecimal> inputs,
                         @Mocked final BigDecimal dotProduct,
                         @Mocked final BigDecimal addition,
                         @Mocked final BigDecimal expected) {

        new MockUp<Neuron>() {
            @Mock(invocations = 1)
            @SuppressWarnings("unused")
            private BigDecimal dotProductWithWeights(final List<BigDecimal> mockInputs) {
                assertThat(mockInputs).isEqualTo(inputs);
                return dotProduct;
            }
        };

        new Expectations() {{
            dotProduct.add(bias); times = 1; result = addition;
            activation.evaluate(addition); times = 1; result = expected;
        }};

        final BigDecimal result = neuron.fire(inputs);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testDotProductWithWeights() {
        setField(neuron, "bias", BigDecimal.ZERO);
        final List<BigDecimal> weights = Arrays.asList(
                BigDecimal.valueOf(95.48294),
                BigDecimal.valueOf(34.68492),
                BigDecimal.valueOf(63.56372)
        );
        setField(neuron, "weights", weights);

        final List<BigDecimal> inputs = Arrays.asList(
                BigDecimal.valueOf(22.34567),
                BigDecimal.valueOf(870.39846),
                BigDecimal.valueOf(1.00034)
        );
        final BigDecimal expected = BigDecimal.valueOf(32386.9165527578);

        final BigDecimal result = invoke(neuron, "dotProductWithWeights", inputs);

        assertThat(result).isEqualByComparingTo(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWithWeights_SizeDiff(@Mocked final BigDecimal bigFloat) {
        final List<BigDecimal> weights = Collections.singletonList(bigFloat);
        final Neuron neuron = Neuron.builder().weights(weights).build();

        final List<BigDecimal> inputs = Arrays.asList(bigFloat, bigFloat, bigFloat);
        invoke(neuron, "dotProductWithWeights", inputs);
    }
}