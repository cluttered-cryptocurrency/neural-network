package com.cluttered.cryptocurrency.ann;

import com.google.gson.Gson;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static mockit.Deencapsulation.getField;
import static mockit.Deencapsulation.setField;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class NeuralNetworkTest {

    @Tested
    @SuppressWarnings("unused")
    private NeuralNetwork neuralNetwork;

    @Injectable
    @SuppressWarnings("unused")
    private final int inputSize = 42;

    @Injectable
    @SuppressWarnings("unused")
    private List<Layer> layers;

    @Test
    public void testBuilder(@Mocked final Activation activation,
                            @Mocked final Builder builder) {
        final int inputSize = 5874;

        new Expectations() {{
            Builder.create(inputSize, activation); times = 1; result = builder;
        }};

        final Builder result = NeuralNetwork.builder(inputSize, activation);

        assertThat(result).isEqualTo(builder);
    }

    @Test
    public void testToJson(@Mocked final String json) {
        final Gson gson = getField(NeuralNetwork.class, "GSON");

        new Expectations(gson) {{
            gson.toJson(neuralNetwork); times = 1; result = json;
        }};

        final String result = neuralNetwork.toJson();

        assertThat(result).isEqualTo(json);
    }

    @Test
    public void testFromJson(@Mocked final String json) {
        final Gson gson = getField(NeuralNetwork.class, "GSON");

        new Expectations(gson) {{
            gson.fromJson(json, NeuralNetwork.class); times = 1; result = neuralNetwork;
        }};

        final NeuralNetwork result = NeuralNetwork.fromJson(json);

        assertThat(result).isEqualTo(neuralNetwork);
    }
}