package dev.pokemonracer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.dto.GenerationDTO;
import dev.pokemonracer.mapper.GenerationMapper;
import dev.pokemonracer.model.Generation;
import dev.pokemonracer.serviceInterfaces.IGenerationService;

@RestController
@RequestMapping("/api/generation")
public class GenerationController {
    
    private IGenerationService generationService;
    private GenerationMapper mapper;

    public GenerationController(IGenerationService generationService){
        this.generationService = generationService;
        this.mapper = new GenerationMapper();
    }

    @GetMapping("")
    public List<GenerationDTO> getAllGenerations(){
        List<Generation> generations = generationService.getAllGenerations();
        List<GenerationDTO> generationDTOs = new ArrayList<GenerationDTO>();
        for (Generation generation : generations) {
            generationDTOs.add(mapper.toDTO(generation));
        }
        return generationDTOs;
    }

    @GetMapping("/{Id}")
    public GenerationDTO getSpecificGeneration(Long id) {
        Generation generation = generationService.getGeneration(id);
        return mapper.toDTO(generation);
    }

    @PostMapping("")
    public void insertGeneration(@RequestParam Long id, @RequestParam int lowerLimit, @RequestParam int upperLimit) {
        generationService.insertGeneration(id, upperLimit, lowerLimit);
    }

    @PutMapping("")
    public void updateGeneration(@RequestParam Long id, @RequestParam int lowerLimit, @RequestParam int upperLimit) {
        generationService.updateGeneration(id, upperLimit, lowerLimit);
    }
}
