package com.cluttered.cryptocurrency.ga;

/**
 * @author cluttered.code@gmail.com
 */
public interface GeneticElement<T extends GeneticElement> {

    /**
     * Returns a new instance of this object with mutated properties at the specified rate.
     *
     * @param mutationRate The rate at which mutation occurs.
     * @return A new instance of this object with mutated properties.
     */
    T mutate(final double mutationRate);

    /**
     * Return a new instance of this object crossed with the specified mate object.
     *
     * @param mate The object to crossover with this object.
     * @return A new instance of this object that has been crossed.
     */
    T crossover(final T mate);
}