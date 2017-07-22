package com.cluttered.cryptocurrency.ga;

import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class GeneticIndividualTest {

    @Test
    public void testCompareTo() {
        final Individual individual1 = new Individual(4.594);
        final Individual individual2 = new Individual(0.583);

        final int result1 = individual1.compareTo(individual2);
        assertThat(result1).isEqualTo(1);

        final int result2 = individual2.compareTo(individual1);
        assertThat(result2).isEqualTo(-1);

        final int result3 = individual1.compareTo(individual1);
        assertThat(result3).isEqualTo(0);
    }


    private static class Individual implements GeneticIndividual<Double> {

        final double fitness;

        Individual(final double fitness) {
            this.fitness = fitness;
        }

        @Override
        public double fitness() {
            return fitness;
        }

        @Override
        public GeneticElement mutate(final double mutationRate) {
            return null;
        }

        @Override
        public GeneticElement crossover(final GeneticElement mate) {
            return null;
        }
    }

}