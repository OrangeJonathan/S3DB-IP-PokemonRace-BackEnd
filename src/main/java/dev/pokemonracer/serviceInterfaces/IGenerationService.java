package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.Generation;

public interface IGenerationService {
    public void InsertGeneration(Long Id, int upperLimit, int lowerLimit);
    public Generation GetGeneration(Long Id);
    public List<Generation> GetAllGenerations();
    public void UpdateGeneration(Long Id, int upperLimit, int lowerLimit);
}
