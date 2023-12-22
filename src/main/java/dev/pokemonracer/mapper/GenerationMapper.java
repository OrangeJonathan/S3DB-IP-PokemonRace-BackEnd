package dev.pokemonracer.mapper;

import org.springframework.stereotype.Component;

import dev.pokemonracer.dto.GenerationDTO;
import dev.pokemonracer.model.Generation;

@Component
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
