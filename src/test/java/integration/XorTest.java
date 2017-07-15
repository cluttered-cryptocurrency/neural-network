package integration;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.Layer;
import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.MathConstants.BIG_FLOAT_CONTEXT_100_HALF_UP;
import static com.cluttered.cryptocurrency.ann.MathConstants.ONE;
import static com.cluttered.cryptocurrency.ann.MathConstants.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
public class XorTest {

    private static BigFloat bigFloat(final String value) {
        return BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(value);
    }

    @Test
    public void xorTest() {
        // Hidden Layer
        final Neuron hiddenNeuron1 = Neuron.builder()
                .sigmoid()
                .weights(bigFloat("60"), bigFloat("60"))
                .bias(bigFloat("-90"))
                .build();
        final Neuron hiddenNeuron2 = Neuron.builder()
                .sigmoid()
                .weights(bigFloat("80"), bigFloat("80"))
                .bias(bigFloat("-40"))
                .build();
        final Layer hiddenLayer = new Layer(Arrays.asList(hiddenNeuron1, hiddenNeuron2));

        // Output Layer
        final Neuron outputNeuron = Neuron.builder()
                .sigmoid()
                .weights(bigFloat("-60"), bigFloat("60"))
                .bias(bigFloat("-30"))
                .build();
        final Layer outputLayer = new Layer(Collections.singletonList(outputNeuron));

        final NeuralNetwork neuralNetwork = new NeuralNetwork(2L, Arrays.asList(hiddenLayer, outputLayer));

        // [<INPUTS>] -> [<OUTPUTS>]
        // [0, 0] -> [0]
        final List<BigFloat> inputs1 = Arrays.asList(ZERO, ZERO);
        final BigFloat result1 = neuralNetwork.fire(inputs1).get(0);
        assertThat(result1.isLessThan(bigFloat("1E-10"))).isTrue();

        // [1, 0] -> [1]
        final List<BigFloat> inputs2 = Arrays.asList(ONE, ZERO);
        final BigFloat result2 = neuralNetwork.fire(inputs2).get(0);
        assertThat(result2.isGreaterThan(bigFloat("0.9999999999")));

        // [0, 1] -> [1]
        final List<BigFloat> inputs3 = Arrays.asList(ZERO, ONE);
        final BigFloat result3 = neuralNetwork.fire(inputs3).get(0);
        assertThat(result3.isGreaterThan(bigFloat("0.9999999999")));

        // [1, 1] -> [0]
        final List<BigFloat> inputs4 = Arrays.asList(ONE, ONE);
        final BigFloat result4 = neuralNetwork.fire(inputs4).get(0);
        assertThat(result4.isLessThan(bigFloat("1E-10"))).isTrue();
    }
}