package integration;

import ch.obermuhlner.math.big.BigFloat;
import com.cluttered.cryptocurrency.ann.Layer;
import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cluttered.cryptocurrency.ann.MathConstants.*;
import static java.math.BigDecimal.ROUND_HALF_UP;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
public class XorTest {

    private static final Logger LOG = LoggerFactory.getLogger(XorTest.class);

    private static BigFloat bigFloat(final String value) {
        return BIG_FLOAT_CONTEXT_100_HALF_UP.valueOf(value);
    }

    public static void xorTest() {
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
        final BigDecimal roundedResult1 = result1.toBigDecimal().setScale(10, ROUND_HALF_UP);
        LOG.info("1) {} -> {}", inputs1, roundedResult1);
        assertThat(roundedResult1).isEqualTo(BigDecimal.ZERO.setScale(10));

        // [1, 0] -> [1]
        final List<BigFloat> inputs2 = Arrays.asList(ONE, ZERO);
        final BigFloat result2 = neuralNetwork.fire(inputs2).get(0);
        final BigDecimal roundedResult2 = result2.toBigDecimal().setScale(10, ROUND_HALF_UP);
        LOG.info("2) {} -> {}", inputs2, roundedResult2);
        assertThat(roundedResult2).isEqualTo(BigDecimal.ONE.setScale(10));

        // [0, 1] -> [1]
        final List<BigFloat> inputs3 = Arrays.asList(ZERO, ONE);
        final BigFloat result3 = neuralNetwork.fire(inputs3).get(0);
        final BigDecimal roundedResult3 = result3.toBigDecimal().setScale(10, ROUND_HALF_UP);
        LOG.info("3) {} -> {}", inputs3, roundedResult3);
        assertThat(roundedResult3).isEqualTo(BigDecimal.ONE.setScale(10));

        // [1, 1] -> [0]
        final List<BigFloat> inputs4 = Arrays.asList(ONE, ONE);
        final BigFloat result4 = neuralNetwork.fire(inputs4).get(0);
        final BigDecimal roundedResult4 = result4.toBigDecimal().setScale(10, ROUND_HALF_UP);
        LOG.info("4) {} -> {}", inputs4, roundedResult4);
        assertThat(roundedResult4).isEqualTo(BigDecimal.ZERO.setScale(10));
    }

    public static void main(final String[] args) {
        xorTest();
        LOG.info("XOR Test Passed");
    }
}