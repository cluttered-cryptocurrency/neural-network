package com.cluttered.cryptocurrency.ga;

import com.cluttered.cryptocurrency.SameChromosomeException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cluttered.cryptocurrency.RandomGenerator.randomBetween;

/**
 * @author cluttered.code@gmail.com
 */
public interface Population<I, T extends Chromosome<I, T>> {

    int size();

    void initializeGeneration();

    List<T> getGeneration();

    void setGeneration(final List<T> generation);

    int getElites();

    double getMutationRate();

    boolean getHalt();

    void setHalt(final boolean halt);

    void storeResults();

    void incrementEpoch();

    long getEpoch();

    Collection<I> getInputs();

    default void trainingLoop() {
        initializeGeneration();
        while (!getHalt()) {
            final Collection<I> inputs = getInputs();
            trainAndSortGeneration(inputs);
            storeResults();
            incrementEpoch();
            crossoverGeneration();
            mutateGeneration();
        }
    }

    default void trainAndSortGeneration(final Collection<I> inputs) {
        final List<T> nextGeneration = getGeneration().stream()
                .peek(chromosome -> chromosome.train(inputs))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        setGeneration(nextGeneration);
    }

    default void crossoverGeneration() {
        final List<T> nextGeneration = new ArrayList<>(size());

        // Add elites twice so second set can be mutated
        final List<T> elites = getGeneration().subList(0, getElites());
        nextGeneration.addAll(elites);
        nextGeneration.addAll(elites);

        final double adjustedTotalFitness = getAdjustedTotalFitness();
        IntStream.range(2 * getElites(), size())
                .forEach(i -> {
                    final T chromosome = selectAndCrossoverPair(adjustedTotalFitness);
                    nextGeneration.add(chromosome);
                });

        elites.forEach(T::reset);
        setGeneration(nextGeneration);
    }

    default double getAdjustedTotalFitness() {
        final double fitnessOffset = getFitnessOffset();
        return getGeneration().stream()
                .mapToDouble(chromosome -> chromosome.fitness() - fitnessOffset)
                .sum();
    }

    default double getFitnessOffset() {
        return getGeneration().get(size() - 1).fitness();
    }

    default T selectAndCrossoverPair(final double adjustedTotalFitness) {
        final T mother = fitnessProportionateSelection(adjustedTotalFitness);
        final T father = fitnessProportionateSelection(adjustedTotalFitness);
        try {
            return mother.crossover(getEpoch(), father);
        } catch (final SameChromosomeException e) {
            return selectAndCrossoverPair(adjustedTotalFitness);
        }
    }

    default T fitnessProportionateSelection(final double adjustedTotalFitness) {
        final double target = randomBetween(0, adjustedTotalFitness);
        final double fitnessOffset = getFitnessOffset();
        double fitnessSum = 0;
        for (final T chromosome : getGeneration()) {
            fitnessSum += chromosome.fitness() - fitnessOffset;
            if (fitnessSum > target)
                return chromosome;
        }
        return getGeneration().get(size() - 1);
    }

    default void mutateGeneration() {
        final List<T> nextGeneration = new ArrayList<>(size());
        // Don't mutate first set of elites
        final List<T> elites = getGeneration().subList(0, getElites());
        nextGeneration.addAll(elites);

        getGeneration().stream()
                .skip(getElites())
                .forEach(chromosome -> {
                    final T mutated = chromosome.mutate(getEpoch(), getMutationRate());
                    nextGeneration.add(mutated);
                });
        setGeneration(nextGeneration);
    }
}