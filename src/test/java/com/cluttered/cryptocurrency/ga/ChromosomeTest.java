package com.cluttered.cryptocurrency.ga;

import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class ChromosomeTest {

    @Test
    public void testCompareTo() {
        final TestChromosome testChromosome1 = new TestChromosome(4.594);
        final TestChromosome testChromosome2 = new TestChromosome(0.583);

        final int result1 = testChromosome1.compareTo(testChromosome2);
        assertThat(result1).isEqualTo(1);

        final int result2 = testChromosome2.compareTo(testChromosome1);
        assertThat(result2).isEqualTo(-1);

        final int result3 = testChromosome1.compareTo(testChromosome1);
        assertThat(result3).isEqualTo(0);
    }


    private static class TestChromosome implements Chromosome<Void, TestChromosome> {

        final double fitness;

        TestChromosome(final double fitness) {
            this.fitness = fitness;
        }

        @Override
        public void train(final Void inputs) {
            throw new UnsupportedOperationException("train() not supported");
        }

        @Override
        public double fitness() {
            return fitness;
        }

        @Override
        public TestChromosome mutate(double mutationRate) {
            throw new UnsupportedOperationException("mutate() not supported");
        }

        @Override
        public TestChromosome crossover(TestChromosome mate) {
            throw new UnsupportedOperationException("crossover() not supported");
        }
    }

}