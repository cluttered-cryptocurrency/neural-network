package integration;

import com.cluttered.cryptocurrency.ann.Layer;
import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import com.cluttered.cryptocurrency.ann.RandomGenerator;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.ann.activation.Activation.TAN_H;

/**
 * @author cluttered.code@gmail.com
 */
public class TimeTest {

    private static Logger LOG = LoggerFactory.getLogger(TimeTest.class);

    private static final int INPUT_SETS = 1000;
    private static final int INPUTS = 200;
    private static final int HIDDEN_NEURONS_1 = 100;
    private static final int HIDDEN_NEURONS_2 = 50;
    private static final int OUTPUTS = 2;

    public static NeuralNetwork build() {
        final Layer hiddenLayer1 = Layer.random(INPUTS, HIDDEN_NEURONS_1);
        final Layer hiddenLayer2 = Layer.random(HIDDEN_NEURONS_1, HIDDEN_NEURONS_2);
        final Layer outputLayer = Layer.random(HIDDEN_NEURONS_2, OUTPUTS, TAN_H);

        return new NeuralNetwork(INPUTS, Arrays.asList(hiddenLayer1, hiddenLayer2, outputLayer));
    }

    public static void main(final String[] args) {
        LOG.info("building NeuralNetwork");
        final NeuralNetwork neuralNetwork = build();
        LOG.info("building Inputs");
        final List<List<Double>> inputSets = IntStream.range(0, INPUT_SETS)
                .parallel()
                .mapToObj(i -> IntStream.range(0, INPUTS)
                        .mapToDouble(j -> Math.random())
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        final int oneYearOfFifteenMinuteIntervals = 4 * 24 * 7 * 52;
        final long startTimeMillis = System.currentTimeMillis();
        IntStream.range(0, oneYearOfFifteenMinuteIntervals)
                .forEach(i -> neuralNetwork.fire(inputSets.get(RandomGenerator.generateRandomIntBoundBy(INPUT_SETS))));
        LOG.error("Test Time: {}ms", System.currentTimeMillis() - startTimeMillis);
    }
}