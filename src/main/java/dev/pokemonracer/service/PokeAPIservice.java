package dev.pokemonracer.service;

import java.util.Set;
import java.security.SecureRandom;
import java.util.HashSet;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;
import dev.pokemonracer.serviceInterfaces.IPokeAPIService;
import dev.pokemonracer.model.Pokemon;

@Service
public class PokeAPIservice implements IPokeAPIService {
 
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
                min = 1;
                max = 1010;
                range = max - min + 1;
                return range;
        }
    }

    public int generateRandomPokemonId(int generationNumber) {
        pokemonGeneration = generationNumber;
        getGenerationRange(generationNumber);
        int id;
        SecureRandom secureRandom = new SecureRandom();
        do {
            id = secureRandom.nextInt(range) + min;
        } while (generatedPokemonIds.contains(id));
        return id;
    }

    public Pokemon getPokemonWithId(int id) throws JsonMappingException, JsonProcessingException {
        generatedPokemonIds.add(id);
        var pokemon = pokemonRepository.getPokemonWithId(id);
        return pokemon;
    }

    public void resetGuessedPokemonList()
    {
        generatedPokemonIds.clear();
    }

    public Set<Integer> getGeneratedPokemonIds() {
        return generatedPokemonIds;
    }

    public void setGeneratedPokemonIds(Set<Integer> generatedPokemonIds) {
        this.generatedPokemonIds = generatedPokemonIds;
    }
}
