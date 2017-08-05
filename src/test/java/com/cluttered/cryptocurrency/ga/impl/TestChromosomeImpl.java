package com.cluttered.cryptocurrency.ga.impl;

import com.cluttered.cryptocurrency.ga.Chromosome;

import java.util.Collection;

public class TestChromosomeImpl implements Chromosome<Double, TestChromosomeImpl> {

    @Override
    public void train(final Collection<Double> inputs) {
        throw new UnsupportedOperationException("train() not supported");
    }

    @Override
    public double fitness() {
        throw new UnsupportedOperationException("fitness() not supported");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("reset() not supported");
    }

    @Override
    public TestChromosomeImpl mutate(final long epoch, final double mutationRate) {
        throw new UnsupportedOperationException("mutate() not supported");
    }

    @Override
    public TestChromosomeImpl crossover(final long epoch, final TestChromosomeImpl mate) {
        throw new UnsupportedOperationException("crossover() not supported");
    }
}