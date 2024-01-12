package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.Generation;

public interface IGenerationService {
    public void insertGeneration(Long Id, int upperLimit, int lowerLimit);
    public Generation getGeneration(Long Id);
    public List<Generation> getAllGenerations();
    public void updateGeneration(Long Id, int upperLimit, int lowerLimit);
}
