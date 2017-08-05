package com.cluttered.cryptocurrency.ga.impl;

import com.cluttered.cryptocurrency.ga.Population;

import java.util.Collection;
import java.util.List;

public class TestPopulationImpl implements Population<Double, TestChromosomeImpl> {

    @Override
    public int size() {
        throw new UnsupportedOperationException("size() not supported");
    }

    @Override
    public void initializeGeneration() {
        throw new UnsupportedOperationException("fitness() not supported");
    }

    @Override
    public List<TestChromosomeImpl> getGeneration() {
        throw new UnsupportedOperationException("getGeneration() not supported");
    }

    @Override
    public void setGeneration(final List<TestChromosomeImpl> generation) {
        throw new UnsupportedOperationException("setGeneration() not supported");
    }

    @Override
    public int getElites() {
        throw new UnsupportedOperationException("getElites() not supported");
    }

    @Override
    public double getMutationRate() {
        throw new UnsupportedOperationException("getMutationRate() not supported");
    }

    @Override
    public boolean getHalt() {
        throw new UnsupportedOperationException("getHalt() not supported");
    }

    @Override
    public void setHalt(final boolean halt) {
        throw new UnsupportedOperationException("setHalt() not supported");
    }

    @Override
    public void incrementEpoch() {
        throw new UnsupportedOperationException("incrementEpoch() not supported");
    }

    @Override
    public Collection<Double> getInputs() {
        throw new UnsupportedOperationException("getInputs() not supported");
    }

    @Override
    public void storeResults() {
        throw new UnsupportedOperationException("storeResults() not supported");
    }
}
