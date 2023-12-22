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
    public List<GenerationDTO> GetAllGenerations(){
        List<Generation> generations = generationService.GetAllGenerations();
        List<GenerationDTO> generationDTOs = new ArrayList<GenerationDTO>();
        for (Generation generation : generations) {
            generationDTOs.add(mapper.ToDTO(generation));
        }
        return generationDTOs;
    }

    @GetMapping("/{Id}")
    public GenerationDTO GetSpecificGeneration(Long Id) {
        Generation generation = generationService.GetGeneration(Id);
        GenerationDTO generationDTO = mapper.ToDTO(generation);
        return generationDTO;
    }

    @PostMapping("")
    public void InsertGeneration(@RequestParam Long Id, @RequestParam int lowerLimit, @RequestParam int upperLimit) {
        generationService.InsertGeneration(Id, upperLimit, lowerLimit);
    }

    @PutMapping("")
    public void UpdateGeneration(@RequestParam Long Id, @RequestParam int lowerLimit, @RequestParam int upperLimit) {
        generationService.UpdateGeneration(Id, upperLimit, lowerLimit);
    }
}
