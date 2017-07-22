package com.cluttered.cryptocurrency.ann;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Random;

import static mockit.Deencapsulation.getField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * @author clutered.code@gmail.com
 */
@RunWith(JMockit.class)
public class RandomGeneratorTest {

    private final Random random = getField(RandomGenerator.class, "RANDOM");

    @Test
    public void testRandom() {
        final double expected = 48392.84;

        new Expectations(random) {{
            random.nextDouble(); times = 1; result = expected;
        }};

        final double result = RandomGenerator.random();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testRandomBias() {
        final double expected = 9403.9584;

        new Expectations(random) {{
            random.nextDouble(); times = 1; result = expected;
        }};

        final double result = RandomGenerator.randomBias();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testRandomLeakage() {
        final double expected = 4.48372;

        new Expectations(random) {{
            random.nextDouble(); times = 1; result = expected;
        }};

        final double result = RandomGenerator.randomLeakage();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testRandomWeight() {
        final double expected = 2222.4774;

        new Expectations(random) {{
            random.nextDouble(); times = 1; result = expected;
        }};

        final double result = RandomGenerator.randomWeight();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCoinFlip() {
        new Expectations(random) {{
            random.nextBoolean(); times = 1; result = true;
        }};

        final boolean result = RandomGenerator.coinFlip();

        assertThat(result).isTrue();
    }

    @Test
    public void testRandomBetween_Min() {
        final double min = -100;
        final double max = 100;

        new Expectations(random) {{
            random.nextDouble(); times = 1; result = 0;
        }};

        final double result = RandomGenerator.randomBetween(min, max);

        assertThat(result).isEqualTo(min);
    }

    @Test
    public void testRandomBetween_Max() {
        final double min = -100;
        final double max = 100;

        new Expectations(random) {{
            random.nextDouble(); times = 1; result = 0.9999999999;
        }};

        final double result = RandomGenerator.randomBetween(min, max);

        assertThat(result).isCloseTo(max, within(0.000001));
    }

    @Test
    public void testRandomWeights() {
        final int size = 3;
        final double result1 = 0.003948;
        final double result2 = 4736.857;
        final double result3 = 1.0000483;

        new Expectations(RandomGenerator.class) {{
            RandomGenerator.randomWeight(); times = size; returns(result1, result2, result3);
        }};

        final List<Double> result = RandomGenerator.randomWeights(size);

        assertThat(result).containsOnly(result1, result2, result3);
    }

    @Test
    public void testRandomNeurons(@Mocked final Activation activation,
                                  @Mocked final Neuron neuron) {
        final int inputSize = 8;
        final int size = 12;

        new Expectations() {{
            Neuron.generate(inputSize, activation); times = size; result = neuron;
        }};

        final List<Neuron> result = RandomGenerator.randomNeurons(inputSize, size, activation);

        assertThat(result.size()).isEqualTo(size);
        assertThat(result).containsOnly(neuron);
    }
}