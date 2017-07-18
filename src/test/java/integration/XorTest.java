package integration;

import com.cluttered.cryptocurrency.ann.Layer;
import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * @author cluttered.code@gmail.com
 */
public class XorTest {

    private static final Logger LOG = LoggerFactory.getLogger(XorTest.class);

//    public void xorTest() {
//        // Hidden Layer
//        final Neuron hiddenNeuron1 = Neuron.builder()
//                .sigmoid()
//                .weights(60.0, 60.0)
//                .bias(-90)
//                .build();
//        final Neuron hiddenNeuron2 = Neuron.builder()
//                .sigmoid()
//                .weights(80.0, 80.0)
//                .bias(-40)
//                .build();
//        final Layer hiddenLayer = new Layer(Arrays.asList(hiddenNeuron1, hiddenNeuron2));
//
//        // Output Layer
//        final Neuron outputNeuron = Neuron.builder()
//                .sigmoid()
//                .weights(-60.0, 60.0)
//                .bias(-30)
//                .build();
//        final Layer outputLayer = new Layer(Collections.singletonList(outputNeuron));
//
//        final NeuralNetwork neuralNetwork = new NeuralNetwork(2, Arrays.asList(hiddenLayer, outputLayer));
//
//        // [<INPUTS>] -> [<OUTPUTS>]
//        // [0, 0] -> [0]
//        final List<Double> inputs1 = Arrays.asList(0.0, 0.0);
//        final Double result1 = neuralNetwork.fire(inputs1).get(0);
//        LOG.info("1) {} -> {}", inputs1, result1);
//        assertThat(result1).isCloseTo(0.0, within(0.0000000000001));
//
//        // [1, 0] -> [1]
//        final List<Double> inputs2 = Arrays.asList(1.0, 0.0);
//        final Double result2 = neuralNetwork.fire(inputs2).get(0);
//        LOG.info("2) {} -> {}", inputs2, result2);
//        assertThat(result1).isCloseTo(1, within(0.99999999999999));
//
//        // [0, 1] -> [1]
//        final List<Double> inputs3 = Arrays.asList(0.0, 1.0);
//        final Double result3 = neuralNetwork.fire(inputs3).get(0);
//        LOG.info("3) {} -> {}", inputs3, result3);
//        assertThat(result1).isCloseTo(1, within(0.99999999999999));
//
//        // [1, 1] -> [0]
//        final List<Double> inputs4 = Arrays.asList(1.0, 1.0);
//        final Double result4 = neuralNetwork.fire(inputs4).get(0);
//        LOG.info("4) {} -> {}", inputs4, result4);
//        assertThat(result1).isCloseTo(0.0, within(0.0000000000001));
//    }
//
//    public static void main(final String[] args) {
//        new XorTest().xorTest();
//        LOG.info("XOR Test PASSED!");
//    }
}