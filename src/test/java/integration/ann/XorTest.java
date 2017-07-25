package integration.ann;

import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class XorTest {

    private static final Logger LOG = LoggerFactory.getLogger(XorTest.class);

    public static void main(final String[] args) throws URISyntaxException, IOException {

        final String xorJson = getXorJson();
        final NeuralNetwork xorNeuralNetwork = NeuralNetwork.fromJson(xorJson);

        // [0, 0] -> [0]
        final List<Double> inputs1 = Arrays.asList(0.0, 0.0);
        final Double result1 = xorNeuralNetwork.fire(inputs1).get(0);
        LOG.error("1) {} -> {}", inputs1, result1);
        assert(isEqualWithin(result1, 0.0, 0.0000000001));

        // [1, 0] -> [1]
        final List<Double> inputs2 = Arrays.asList(1.0, 0.0);
        final Double result2 = xorNeuralNetwork.fire(inputs2).get(0);
        LOG.error("2) {} -> {}", inputs2, result2);
        assert(isEqualWithin(result2, 1.0, 0.0000000001));

        // [0, 1] -> [1]
        final List<Double> inputs3 = Arrays.asList(0.0, 1.0);
        final Double result3 = xorNeuralNetwork.fire(inputs3).get(0);
        LOG.error("3) {} -> {}", inputs3, result3);
        assert(isEqualWithin(result3, 1.0, 0.0000000001));

        // [1, 1] -> [0]
        final List<Double> inputs4 = Arrays.asList(1.0, 1.0);
        final Double result4 = xorNeuralNetwork.fire(inputs4).get(0);
        LOG.error("4) {} -> {}", inputs4, result4);
        assert(isEqualWithin(result4, 0.0, 0.0000000001));
    }

    private static String getXorJson() throws URISyntaxException, IOException {
        final URI uri = ClassLoader.getSystemClassLoader().getResource("xor.json").toURI();
        final Path path = Paths.get(uri);
        final String xorJson = new String(Files.readAllBytes(path));
        LOG.error("Loading NeuralNetwork from json:\n{}", xorJson);
        return xorJson;
    }

    private static boolean isEqualWithin(final double result, final double expected, final double tolerance) {
        return Math.abs(result - expected) < tolerance;
    }
}
