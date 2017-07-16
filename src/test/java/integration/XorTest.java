package integration;

import com.cluttered.cryptocurrency.ann.Layer;
import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
public class XorTest {

    private static final Logger LOG = LoggerFactory.getLogger(XorTest.class);

    private static BigDecimal bigDecimal(final String value) {
        return new BigDecimal(value);
    }

    @Test
    public void xorTest() {
        // Hidden Layer
        final Neuron hiddenNeuron1 = Neuron.builder()
                .sigmoid()
                .weights(bigDecimal("60"), bigDecimal("60"))
                .bias(bigDecimal("-90"))
                .build();
        final Neuron hiddenNeuron2 = Neuron.builder()
                .sigmoid()
                .weights(bigDecimal("80"), bigDecimal("80"))
                .bias(bigDecimal("-40"))
                .build();
        final Layer hiddenLayer = new Layer(Arrays.asList(hiddenNeuron1, hiddenNeuron2));

        // Output Layer
        final Neuron outputNeuron = Neuron.builder()
                .sigmoid()
                .weights(bigDecimal("-60"), bigDecimal("60"))
                .bias(bigDecimal("-30"))
                .build();
        final Layer outputLayer = new Layer(Collections.singletonList(outputNeuron));

        final NeuralNetwork neuralNetwork = new NeuralNetwork(2L, Arrays.asList(hiddenLayer, outputLayer));

        // [<INPUTS>] -> [<OUTPUTS>]
        // [0, 0] -> [0]
        final List<BigDecimal> inputs1 = Arrays.asList(ZERO, ZERO);
        final BigDecimal result1 = neuralNetwork.fire(inputs1).get(0).setScale(10, ROUND_HALF_UP);
        LOG.info("1) {} -> {}", inputs1, result1);
        assertThat(result1).isEqualTo(ZERO.setScale(10));

        // [1, 0] -> [1]
        final List<BigDecimal> inputs2 = Arrays.asList(ONE, ZERO);
        final BigDecimal result2 = neuralNetwork.fire(inputs2).get(0).setScale(10, ROUND_HALF_UP);
        LOG.info("2) {} -> {}", inputs2, result2);
        assertThat(result2).isEqualTo(ONE.setScale(10));

        // [0, 1] -> [1]
        final List<BigDecimal> inputs3 = Arrays.asList(ZERO, ONE);
        final BigDecimal result3 = neuralNetwork.fire(inputs3).get(0).setScale(10, ROUND_HALF_UP);
        LOG.info("3) {} -> {}", inputs3, result3);
        assertThat(result3).isEqualTo(ONE.setScale(10));

        // [1, 1] -> [0]
        final List<BigDecimal> inputs4 = Arrays.asList(ONE, ONE);
        final BigDecimal result4 = neuralNetwork.fire(inputs4).get(0).setScale(10, ROUND_HALF_UP);
        LOG.info("4) {} -> {}", inputs4, result4);
        assertThat(result4).isEqualTo(ZERO.setScale(10));
    }
}