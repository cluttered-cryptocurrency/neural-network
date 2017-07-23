package integration;

import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.ann.Activation.ELU;

/**
 * @author cluttered.code@gmail.com
 */
public class PerformanceTest {

    private static Logger LOG = LoggerFactory.getLogger(PerformanceTest.class);

    private static final int INPUT_SETS = 4 * 24 * 365; // 1 year of 15 minute intervals

    private static final int INPUTS = 200;
    private static final int HIDDEN_1 = 100;
    private static final int HIDDEN_2 = 50;
    private static final int OUTPUTS = 3;

    public static void main(final String[] args) {
        LOG.error("Building NeuralNetwork {} -> {} -> {} -> {} ->", INPUTS, HIDDEN_1, HIDDEN_2, OUTPUTS);
        final NeuralNetwork neuralNetwork = NeuralNetwork.builder(INPUTS, ELU)
                .addLayer(HIDDEN_1)
                .addLayer(HIDDEN_2)
                .addLayer(OUTPUTS)
                .build();

        LOG.error("Building Input Sets");
        final List<List<Double>> inputSets = IntStream.range(0, INPUT_SETS)
                .parallel()
                .mapToObj(i -> IntStream.range(0, INPUTS)
                        .mapToDouble(j -> Math.random())
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        final Random random = new Random();

        LOG.error("Testing...");
        final long startTimeMillis = System.currentTimeMillis();
        inputSets.parallelStream()
                .forEach(neuralNetwork::fire);
        LOG.error("Test Time: {}ms", System.currentTimeMillis() - startTimeMillis);
    }
}