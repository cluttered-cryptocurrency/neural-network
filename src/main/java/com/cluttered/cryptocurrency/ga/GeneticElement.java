package com.cluttered.cryptocurrency.ga;

public interface GeneticElement<T extends GeneticElement> {

    T mutate(final double mutationRate);

    T crossover(final T mate);
}
