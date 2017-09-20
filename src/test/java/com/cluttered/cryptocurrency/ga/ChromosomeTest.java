package com.cluttered.cryptocurrency.ga;

import com.cluttered.cryptocurrency.ga.impl.TestChromosomeImpl;
import mockit.Expectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cluttered.code@gmail.com
 */
@RunWith(JMockit.class)
public class ChromosomeTest {

    @Tested
    @SuppressWarnings("unused")
    private TestChromosomeImpl chromosome;

    @Test
    public void testCompareTo_GreaterThan() {

        final double bigFitness = 4.594;
        final double littleFitness = 0.583;

        new Expectations(chromosome) {{
            chromosome.fitness(); times = 2; returns(bigFitness, littleFitness);
        }};

        final int result1 = chromosome.compareTo(chromosome);
        assertThat(result1).isEqualTo(1);
    }

    @Test
    public void testCompareTo_LessThan() {

        final double bigFitness = 4.594;
        final double littleFitness = 0.583;

        new Expectations(chromosome) {{
            chromosome.fitness(); times = 2; returns(littleFitness, bigFitness);
        }};

        final int result1 = chromosome.compareTo(chromosome);
        assertThat(result1).isEqualTo(-1);
    }

    @Test
    public void testCompareTo_Equal() {

        final double fitness = 4.594;

        new Expectations(chromosome) {{
            chromosome.fitness(); times = 2; result = fitness;
            chromosome.getEpoch(); times = 2; returns(5L, 0L);
        }};

        final int result1 = chromosome.compareTo(chromosome);
        assertThat(result1).isEqualTo(1);
    }
}