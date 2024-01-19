package dev.pokemonracer.mapper;

import org.springframework.stereotype.Component;

import dev.pokemonracer.dto.GenerationDTO;
import dev.pokemonracer.model.Generation;

@Component
public class GenerationMapper {
    public Generation toEntity(GenerationDTO generationDTO){
        return new Generation(generationDTO.getId(), generationDTO.getLowerLimit(), generationDTO.getUpperLimit());
    }
    public GenerationDTO toDTO(Generation generation){
        return new GenerationDTO(generation.getId(), generation.getLowerLimit(), generation.getUpperLimit());
    }
}
