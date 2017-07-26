package com.cluttered.cryptocurrency.ga;

import java.util.Collection;

/**
 * @author cluttered.code@gmail.com
 */
public interface Chromosome<I, T extends Chromosome> extends GeneticElement<T>, Comparable<T> {

    /**
     * Trains this {@code Chromosome} with the specified inputs to determine its fitness.
     *
     * @param inputs The {@code Collection} of inputs used to train this {@code Chromosome}.
     */
    void train(final Collection<I> inputs);

    /**
     * The value used to rank with {@code Chromosome} with others.
     *
     * @return The rank value of this {@code Chromosome}.
     */
    double fitness();

    /**
     * Compares this {@code Chromosome} instance's fitness to another instance's fitness for rank sorting.
     *
     * @param chromosome the {@code Chromosome} object being compared to this instance.
     * @return The compare value (1, 0, -1).
     */
    default int compareTo(final T chromosome) {
        return Double.compare(this.fitness(), chromosome.fitness());
    }
}