package com.cluttered.cryptocurrency.ga;

import com.cluttered.cryptocurrency.ga.impl.TestPopulationImpl;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import java.util.Collection;

public class PopulationTest {

    @Tested
    @SuppressWarnings("unused")
    private TestPopulationImpl population;

    @Test
    public void testTrainingLoop(@Mocked final Collection<Double> inputs) {

        new Expectations(population) {{
            population.initializeGeneration(); times = 1;
            population.getHalt(); times = 3; returns(false, false, true);
            population.trainAndSortGeneration(inputs); times = 2;
            population.crossoverGeneration(); times = 2;
            population.mutateGeneration(); times = 2;
            population.incrementGenerationCount(); times = 2;
            population.storeNextGeneration();
        }};

        population.trainingLoop(inputs);
    }

}