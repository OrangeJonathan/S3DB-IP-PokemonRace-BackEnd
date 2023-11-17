package dev.pokemonracer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;

import dev.pokemonracer.model.Pokemon;

@Service
public class PokeAPIservice {
 
    private Set<Integer> generatedPokemonIds = new HashSet<>(); 

    private int pokemonGeneration = 0;
    private int max;
    private int min;
    private int range;

    //@Autowired
    private IPokemonRepository pokemonRepository;

    public PokeAPIservice(IPokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public void setPokemonGeneration(int generation)
    {
        pokemonGeneration = generation;
    }

    public int getPokemonGeneration(){
        return pokemonGeneration;
    }
    
    private int getGenerationRange(int generationNumber) {
        switch (generationNumber) {
            case 0:
                min = 1;
                max = 1010;
                range = max - min + 1;
                return range;
            case 1:
                min = 1;
                max = 151;
                range = max - min + 1;
                return range;
            case 2:
                min = 152;
                max = 251;
                range = max - min + 1;
                return range;
            case 3:
                min = 252;
                max = 386;
                range = max - min + 1;
                return range;
            case 4:
                min = 387;
                max = 493;
                range = max - min + 1;
                return range;
            case 5:
                min = 494;
                max = 649;
                range = max - min + 1;
                return range;
            case 6:
                min = 650;
                max = 721;
                range = max - min + 1;
                return range;
            case 7:
                min = 722;
                max = 809;
                range = max - min + 1;
                return range;
            case 8:
                min = 810;
                max = 898;
                range = max - min + 1;
                return range;
            case 9: 
                min = 899;
                max = 1010;
                range = max - min + 1;
                return range;
            default:
                return 0;
        }
    }

    public int generateRandomPokemonId() {
        int id;
        do {
            id = (int) (Math.random() * getGenerationRange(pokemonGeneration)) + min;
            System.out.println("min:" + min + " max:" + max + " range:" + range + " id:" + id);
        } while (generatedPokemonIds.contains(id)); 
        return id;
    }

    public Pokemon getPokemonWithId(int id) throws JsonMappingException, JsonProcessingException {
        generatedPokemonIds.add(id);
        var pokemon = pokemonRepository.getPokemonWithId(id);
        System.out.println("pokemon: " + pokemon.getName());
        return pokemon; 
    }

    public void resetGuessedPokemonList()
    {
        generatedPokemonIds.clear();
        System.out.println("reset");
    }

    public Set<Integer> getGeneratedPokemonIds() {
        return generatedPokemonIds;
    }

    public void setGeneratedPokemonIds(Set<Integer> generatedPokemonIds) {
        this.generatedPokemonIds = generatedPokemonIds;
    }
}
