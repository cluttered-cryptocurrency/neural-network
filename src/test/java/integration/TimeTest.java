package integration;

import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import com.cluttered.cryptocurrency.ann.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.ann.activation.Activation.SIGMOID;
import static com.cluttered.cryptocurrency.ann.activation.Activation.TAN_H;

/**
 * @author cluttered.code@gmail.com
 */
public class TimeTest {

    private static Logger LOG = LoggerFactory.getLogger(TimeTest.class);

    private static final int INPUT_SETS = 1000;
    
    private static final int INPUTS = 200;
    private static final int HIDDEN_1 = 100;
    private static final int HIDDEN_2 = 50;
    private static final int OUTPUTS = 2;

    public static void main(final String[] args) {
        LOG.error("Building NeuralNetwork {} -> {} -> {} -> {} ->", INPUTS, HIDDEN_1, HIDDEN_2, OUTPUTS);
        final NeuralNetwork neuralNetwork = NeuralNetwork.builder(INPUTS)
                .addLayer(HIDDEN_1, TAN_H)
                .addLayer(HIDDEN_2, TAN_H)
                .addOutputLayer(OUTPUTS, TAN_H);

        LOG.error("Building Input Sets");
        final List<List<Double>> inputSets = IntStream.range(0, INPUT_SETS)
                .parallel()
                .mapToObj(i -> IntStream.range(0, INPUTS)
                        .mapToDouble(j -> Math.random())
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        LOG.error("Testing...");
        final int oneYearOfFifteenMinuteIntervals = 4 * 24 * 7 * 52;
        final long startTimeMillis = System.currentTimeMillis();
        IntStream.range(0, oneYearOfFifteenMinuteIntervals)
                .forEach(i -> neuralNetwork.fire(inputSets.get(RandomGenerator.generateRandomIntBoundBy(INPUT_SETS))));
        LOG.error("Test Time: {}ms", System.currentTimeMillis() - startTimeMillis);
    }
}