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

    public void insertGeneration(Long Id, int upperLimit, int lowerLimit) {
        Generation generation = new Generation(Id, upperLimit, lowerLimit);
        if (generationRepository.findById(Id).isPresent()) return;
        generationRepository.save(generation);
    }

    public Generation getGeneration(Long Id){
        Optional<Generation> optionalGeneration = generationRepository.findById(Id);
        if (optionalGeneration.isEmpty()) return null;
        return optionalGeneration.get();
    }

    public List<Generation> getAllGenerations()
    {
        return generationRepository.findAll();
    }

    public void updateGeneration(Long Id, int upperLimit, int lowerLimit) {
        Generation generation = getGeneration(Id);
        if (generation == null) return;

        generation.setLowerLimit(lowerLimit);
        generation.setUpperLimit(upperLimit);

        generationRepository.save(generation);
    }
}
