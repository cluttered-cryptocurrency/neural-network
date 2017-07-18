package integration;

import com.cluttered.cryptocurrency.ann.Layer;
import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import com.cluttered.cryptocurrency.ann.neuron.Neuron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author cluttered.code@gmail.com
 */
public class TimeTest {

    private static Logger LOG = LoggerFactory.getLogger(TimeTest.class);

    private static final int INPUTS = 200;
    private static final int HIDDEN_NODES_1 = 150;
    private static final int HIDDEN_NODES_2 = 75;
    private static final int HIDDEN_NODES_3 = 25;
    private static final int OUTPUTS = 2;

    public static NeuralNetwork build() {
        final List<Neuron> hiddenNeurons1 = IntStream.range(0, HIDDEN_NODES_1)
                .mapToObj(i -> {
                    final List<Double> weights = IntStream.range(0, INPUTS)
                            .mapToDouble(j -> Math.random())
                            .boxed()
                            .collect(Collectors.toList());
                    return Neuron.builder()
                            .weights(weights)
                            .tanH()
                            .build();
                }).collect(Collectors.toList());
        final Layer hiddenLayer1 = new Layer(hiddenNeurons1);

        final List<Neuron> hiddenNeurons2 = IntStream.range(0, HIDDEN_NODES_2)
                .mapToObj(i -> {
                    final List<Double> weights = IntStream.range(0, HIDDEN_NODES_1)
                            .mapToDouble(j -> Math.random())
                            .boxed()
                            .collect(Collectors.toList());
                    return Neuron.builder()
                            .weights(weights)
                            .tanH()
                            .build();
                }).collect(Collectors.toList());
        final Layer hiddenLayer2 = new Layer(hiddenNeurons2);

        final List<Neuron> hiddenNeurons3 = IntStream.range(0, HIDDEN_NODES_3)
                .mapToObj(i -> {
                    final List<Double> weights = IntStream.range(0, HIDDEN_NODES_2)
                            .mapToDouble(j -> Math.random())
                            .boxed()
                            .collect(Collectors.toList());
                    return Neuron.builder()
                            .weights(weights)
                            .tanH()
                            .build();
                }).collect(Collectors.toList());
        final Layer hiddenLayer3 = new Layer(hiddenNeurons3);

        final List<Neuron> outputNeurons = IntStream.range(0, OUTPUTS)
                .mapToObj(i -> {
                    final List<Double> weights = IntStream.range(0, HIDDEN_NODES_3)
                            .mapToDouble(j -> Math.random())
                            .boxed()
                            .collect(Collectors.toList());
                    return Neuron.builder()
                            .weights(weights)
                            .tanH()
                            .build();
                }).collect(Collectors.toList());
        final Layer outputLayer = new Layer(outputNeurons);

        return new NeuralNetwork(INPUTS, Arrays.asList(hiddenLayer1, hiddenLayer2, hiddenLayer3, outputLayer));
    }

    public static void main(final String[] args) {
        LOG.info("building NeuralNetwork");
        final NeuralNetwork neuralNetwork = build();
        LOG.info("building Inputs");
        final List<Double> inputs = IntStream.range(0, INPUTS)
                .mapToDouble(i -> Math.random())
                .boxed()
                .collect(Collectors.toList());

        final int oneYearOfFifteenMinuteIntervals = 4*24*7*52;
        final long startTimeMillis = System.currentTimeMillis();
        IntStream.range(0, oneYearOfFifteenMinuteIntervals)
                .forEach(i -> neuralNetwork.fire(inputs));
        LOG.info("Test Time: {}ms", System.currentTimeMillis() - startTimeMillis);
    }
}