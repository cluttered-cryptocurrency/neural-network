package com.cluttered.cryptocurrency.ga.impl;

import com.cluttered.cryptocurrency.ga.Population;

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
    public void incrementGenerationCount() {
        throw new UnsupportedOperationException("incrementGenerationCount() not supported");
    }

    @Override
    public void storeNextGeneration() {
        throw new UnsupportedOperationException("storeNextGeneration() not supported");
    }
}
