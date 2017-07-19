package integration;

import com.cluttered.cryptocurrency.ann.NeuralNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonTest {

    private static final Logger LOG = LoggerFactory.getLogger(JsonTest.class);


    public static void main(final String[] args) {
        final String json = NeuralNetwork.builder(25)
                .addLayer(15)
                .addLayer(5)
                .addOutputLayer(2)
                .toJson();
        LOG.info(json);
    }

}
