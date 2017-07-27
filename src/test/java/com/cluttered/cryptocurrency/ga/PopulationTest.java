package com.cluttered.cryptocurrency.ga;

import com.cluttered.cryptocurrency.RandomGenerator;
import com.cluttered.cryptocurrency.ga.impl.TestChromosomeImpl;
import com.cluttered.cryptocurrency.ga.impl.TestPopulationImpl;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void testCrossoverGeneration(@Mocked final TestChromosomeImpl chromosome) {
        final List<TestChromosomeImpl> generation = Arrays.asList(chromosome, chromosome, chromosome, chromosome);

        final int size = 4;
        final int elites = 1;
        final double adjustedTotalFitness = 57832.86;

        new Expectations(population) {{
            population.size(); times = 2; result = size;
            population.getElites(); times = 2; result = elites;
            population.getGeneration(); times = 1; result = generation;
            population.getAdjustedTotalFitness(); times = 1; result = adjustedTotalFitness;
            population.selectAndCrossoverPair(adjustedTotalFitness); times = size - (2*elites); result = chromosome;
            population.setGeneration((List<TestChromosomeImpl>) any); times = 1;
        }};

        population.crossoverGeneration();
    }

    @Test
    public void testGetAdjustedTotalFitness(@Mocked final TestChromosomeImpl chromosome) {
        final List<TestChromosomeImpl> generation = Arrays.asList(chromosome, chromosome, chromosome);
        final double offset = -12.847;
        final double fitness = 7.934;

        new Expectations(population) {{
            population.getFitnessOffset(); times = 1; result = offset;
            population.getGeneration(); times = 1; result = generation;
            chromosome.fitness(); times = generation.size(); result = fitness;
        }};

        final double result = population.getAdjustedTotalFitness();

        assertThat(result).isEqualTo(generation.size() * (fitness - offset));
    }

    @Test
    public void testGetFitnessOffset(@Mocked final List<TestChromosomeImpl> generation,
                                     @Mocked final TestChromosomeImpl chromosome) {
        final int size = 36;
        final double fitness = -83;

        new Expectations(population) {{
            population.size(); times = 1; result = 36;
            population.getGeneration(); times = 1; result = generation;
            generation.get(size-1); times = 1; result = chromosome;
            chromosome.fitness(); times = 1; result = fitness;
        }};

        final double result = population.getFitnessOffset();

        assertThat(result).isEqualTo(fitness);
    }

    @Test
    public void testSelectAndCrossoverPair(@Mocked final TestChromosomeImpl mother,
                                           @Mocked final TestChromosomeImpl father,
                                           @Mocked final TestChromosomeImpl child) {
        final double adjustedTotalFitness = 99.333;

        new Expectations(population) {{
            population.fitnessProportionateSelection(adjustedTotalFitness); times = 2; returns(mother, father);
            mother.crossover(father); times = 1; result = child;
        }};

        final TestChromosomeImpl result = population.selectAndCrossoverPair(adjustedTotalFitness);

        assertThat(result).isEqualTo(child);
    }

    @Test
    public void testFitnessProportionateSelection(@Mocked final TestChromosomeImpl chromosome,
                                                  @Mocked final TestChromosomeImpl targetChromosome) {
        final double adjustedTotalFitness = 9.8;
        final double target = 4.5;
        final double offset = 1.0;
        final List<TestChromosomeImpl> generation = Arrays.asList(chromosome, targetChromosome, chromosome);

        new Expectations(RandomGenerator.class, population) {{
            RandomGenerator.randomBetween(0, adjustedTotalFitness); times = 1; result = target;
            population.getFitnessOffset(); times = 1; result = offset;
            population.getGeneration(); times = 1; result = generation;
            chromosome.fitness(); times = 1; result = 4.0;
            targetChromosome.fitness(); times = 1; result = 3.0;
        }};

        final TestChromosomeImpl result = population.fitnessProportionateSelection(adjustedTotalFitness);

        assertThat(result).isEqualTo(targetChromosome);
    }

    @Test
    public void testFitnessProportionateSelection_Last(@Mocked final TestChromosomeImpl chromosome,
                                                  @Mocked final TestChromosomeImpl targetChromosome) {
        final double adjustedTotalFitness = 9.8;
        final double target = 9.8;
        final double offset = 1.0;
        final List<TestChromosomeImpl> generation = Arrays.asList(chromosome, chromosome, targetChromosome);

        new Expectations(RandomGenerator.class, population) {{
            RandomGenerator.randomBetween(0, adjustedTotalFitness); times = 1; result = target;
            population.getFitnessOffset(); times = 1; result = offset;
            population.getGeneration(); times = 2; result = generation;
            chromosome.fitness(); times = 1; returns(5.0, 4.0);
            targetChromosome.fitness(); times = 1; result = 3.8;
            population.size(); times = 1; result = generation.size();
        }};

        final TestChromosomeImpl result = population.fitnessProportionateSelection(adjustedTotalFitness);

        assertThat(result).isEqualTo(targetChromosome);
    }
}