package dev.pokemonracer.mapper;

import dev.pokemonracer.DTOs.GenerationDTO;
import dev.pokemonracer.model.Generation;

public class GenerationMapper {
    public Generation ToEntity(GenerationDTO generationDTO){
        Generation generation = new Generation(generationDTO.getId(), generationDTO.getLowerLimit(), generationDTO.getUpperLimit());
        return generation;
    }
    public GenerationDTO ToDTO(Generation generation){
        GenerationDTO generationDTO = new GenerationDTO(generation.getId(), generation.getLowerLimit(), generation.getUpperLimit());
        return generationDTO;
    }
}
