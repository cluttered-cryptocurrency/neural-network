package com.cluttered.cryptocurrency.ga;

import com.cluttered.cryptocurrency.ga.impl.TestChromosomeImpl;
import com.cluttered.cryptocurrency.ga.impl.TestPopulationImpl;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

    @Test
    public void testTrainAndSortGeneration(@Mocked final Collection<Double> inputs,
                                           @Mocked final TestChromosomeImpl chromosome) {
        final List<TestChromosomeImpl> generation = Arrays.asList(chromosome, chromosome, chromosome);

        new Expectations(population) {{
            population.getGeneration(); times = 1; result = generation;
            //chromosome.train(inputs); times = generation.size();
            population.setGeneration((List<TestChromosomeImpl>) any); times = 1;
        }};

        population.trainAndSortGeneration(inputs);
    }
}