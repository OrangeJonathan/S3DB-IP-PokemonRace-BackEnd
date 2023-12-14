package dev.pokemonracer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.pokemonracer.model.Generation;
import dev.pokemonracer.repository.GenerationRepository;
import dev.pokemonracer.serviceInterfaces.IGenerationService;

@Service
public class GenerationService implements IGenerationService {
    
    private GenerationRepository generationRepository;

    public GenerationService(GenerationRepository generationRepository){ 
        this.generationRepository = generationRepository;
    }

    public void InsertGeneration(Long Id, int upperLimit, int lowerLimit) {
        Generation generation = new Generation(Id, upperLimit, lowerLimit);
        if (generationRepository.findById(Id) != null) return;
        generationRepository.save(generation);
    }

    public Generation GetGeneration(Long Id){
        Optional<Generation> optionalGeneration = generationRepository.findById(Id);
        if (optionalGeneration == null) return null;
        Generation generation = optionalGeneration.get();

        return generation;
    }

    public List<Generation> GetAllGenerations()
    {
        List<Generation> generations = generationRepository.findAll();
        return generations;
    }

    public void UpdateGeneration(Long Id, int upperLimit, int lowerLimit) {
        Generation generation = GetGeneration(Id);
        if (generation == null) return;

        generation.setLowerLimit(lowerLimit);
        generation.setUpperLimit(upperLimit);

        generationRepository.save(generation);
    }
}
