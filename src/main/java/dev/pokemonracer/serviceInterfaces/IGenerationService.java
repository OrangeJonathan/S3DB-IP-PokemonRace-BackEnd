package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.Generation;

public interface IGenerationService {
    public void insertGeneration(Long id, int upperLimit, int lowerLimit);
    public Generation getGeneration(Long id);
    public List<Generation> getAllGenerations();
    public void updateGeneration(Long id, int upperLimit, int lowerLimit);
}
