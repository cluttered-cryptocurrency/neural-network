package com.cluttered.cryptocurrency.ga;

public interface GeneticIndividual<I> extends GeneticElement, Comparable<GeneticIndividual<I>> {

    double fitness();

    default int compareTo(final GeneticIndividual<I> individual) {
        return Double.compare(this.fitness(), individual.fitness());
    }
}