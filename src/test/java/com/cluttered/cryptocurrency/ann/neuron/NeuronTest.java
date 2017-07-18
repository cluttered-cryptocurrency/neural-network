package com.cluttered.cryptocurrency.ann.neuron;

import com.cluttered.cryptocurrency.ann.activation.Activation;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.activation.Activation.LINEAR;
import static java.lang.Double.*;
import static mockit.Deencapsulation.invoke;
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
    private Double bias;

    @Injectable
    @SuppressWarnings("unused")
    private List<Double> weights;

    @Injectable
    @SuppressWarnings("unused")
    private final Activation activation = LINEAR;

    @Test
    public void testBuilder(@Mocked final NeuronBuilder neuronBuilder) {
        new Expectations() {{
            NeuronBuilder.create(); times = 1; result = neuronBuilder;
        }};

        final NeuronBuilder result = Neuron.builder();

        assertThat(result).isEqualTo(neuronBuilder);
    }

    @Test
    public void testFire(@Mocked final List<Double> inputs) {

        final double dotProduct = 2.57684;
        final double bias = 1.0;
        setField(neuron, "bias", bias);
        final double expected = 32.573;

        new MockUp<Neuron>() {
            @Mock(invocations = 1)
            @SuppressWarnings("unused")
            private double dotProductWithWeights(final List<Double> mockInputs) {
                assertThat(mockInputs).isEqualTo(inputs);
                return dotProduct;
            }
        };

        new Expectations() {{
            activation.evaluate(dotProduct + bias); times = 1; result = expected;
        }};

        final Double result = neuron.fire(inputs);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testDotProductWithWeights() {
        final List<Double> weights = Arrays.asList(
                95.48294,
                34.68492,
                63.56372
        );
        setField(neuron, "weights", weights);

        final List<Double> inputs = Arrays.asList(
                22.34567,
                870.39846,
                1.00034
        );
        final Double expected = 32386.916552757797;

        final Double result = invoke(neuron, "dotProductWithWeights", inputs);

        assertThat(result).isEqualTo(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWithWeights_SizeDiff() {
        setField(neuron, "weights", Collections.singletonList(NaN));

        final List<Double> inputs = Arrays.asList(NEGATIVE_INFINITY, 0.0, POSITIVE_INFINITY);
        invoke(neuron, "dotProductWithWeights", inputs);
    }
}