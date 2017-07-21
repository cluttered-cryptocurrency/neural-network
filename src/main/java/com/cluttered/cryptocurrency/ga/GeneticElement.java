package com.cluttered.cryptocurrency.ga;

/**
 * @author cluttered.code@gmail.com
 */
public interface GeneticElement<T extends GeneticElement> {

    T mutate(final double mutationRate);

    T crossover(final T mate);
}