package com.cluttered.cryptocurrency.ga;

import com.cluttered.cryptocurrency.SameChromosomeException;

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
     * The value epoch in which this {@code Chromosome} was created.
     *
     * @return The epoch in which this {@code Chromosome} was created.
     */
    long getEpoch();


    /**
     * Called on elites being added to the next generation for cleanup.
     */
    void reset();

    /**
     * Compares this {@code Chromosome} instance's fitness to another instance's fitness for rank sorting.
     * If the fitness values are equivalent it will rank the one with the lesser epoch higher.
     *
     * @param chromosome the {@code Chromosome} object being compared to this instance.
     * @return The compare value (1, 0, -1).
     */
    default int compareTo(final T chromosome) {
        int compare = Double.compare(this.fitness(), chromosome.fitness());
        if(compare == 0)
            return Long.compare(chromosome.getEpoch(), this.getEpoch());
        return compare;
    }

    T mutate(final long epoch, final double mutationRate);

    @Override
    default T mutate(final double mutationRate) {
        throw new UnsupportedOperationException("mutate(mutationRate) not supported use mutate(epoch, mutationRate) instead");
    }

    T crossover(final long epoch, final T chromosome) throws SameChromosomeException;

    @Override
    default T crossover(final T chromosome) {
        throw new UnsupportedOperationException("crossover(chromosome) not supported use crossover(epoch, chromosome) instead");
    }
}