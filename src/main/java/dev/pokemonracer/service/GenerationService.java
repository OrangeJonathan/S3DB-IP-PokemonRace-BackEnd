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

    public void insertGeneration(Long id, int upperLimit, int lowerLimit) {
        Generation generation = new Generation(id, upperLimit, lowerLimit);
        if (generationRepository.findById(id).isPresent()) return;
        generationRepository.save(generation);
    }

    public Generation getGeneration(Long id){
        Optional<Generation> optionalGeneration = generationRepository.findById(id);
        if (optionalGeneration.isEmpty()) return null;
        return optionalGeneration.get();
    }

    public List<Generation> getAllGenerations()
    {
        return generationRepository.findAll();
    }

    public void updateGeneration(Long id, int upperLimit, int lowerLimit) {
        Generation generation = getGeneration(id);
        if (generation == null) return;

        generation.setLowerLimit(lowerLimit);
        generation.setUpperLimit(upperLimit);

        generationRepository.save(generation);
    }
}
