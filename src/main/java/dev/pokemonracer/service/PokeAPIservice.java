package dev.pokemonracer.service;

import java.util.Set;
import java.security.SecureRandom;
import java.util.HashSet;
import org.springframework.stereotype.Service;

import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;
import dev.pokemonracer.serviceInterfaces.IGenerationService;
import dev.pokemonracer.serviceInterfaces.IPokeAPIService;
import dev.pokemonracer.exceptions.PokemonApiException;
import dev.pokemonracer.model.Generation;
import dev.pokemonracer.model.Pokemon;

@Service
public class PokeAPIservice implements IPokeAPIService {
 
    private Set<Integer> generatedPokemonIds = new HashSet<>(); 

    private int max;
    private int min;
    private int range;

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getRange() {
        return range;
    }

    private IPokemonRepository pokemonRepository;
    private IGenerationService generationService;

    public PokeAPIservice(IPokemonRepository pokemonRepository, IGenerationService generationService) {
        this.pokemonRepository = pokemonRepository;
        this.generationService = generationService;
    }

    private void SetGeneration(int Id) {
        Generation generation = generationService.GetGeneration(Long.valueOf(Id));
        max = generation.getUpperLimit();
        min = generation.getLowerLimit();
        range = max - min;
    }

    public int generateRandomPokemonId(int generationNumber) {
        int id;
        SetGeneration(generationNumber);
        SecureRandom secureRandom = new SecureRandom();
        do {
            id = secureRandom.nextInt(range) + min;
        } while (generatedPokemonIds.contains(id));
        return id;
    }

    public Pokemon getPokemonWithId(int id) {
        try {
            generatedPokemonIds.add(id);
            var pokemon = pokemonRepository.getPokemonWithId(id);
            return pokemon;
        } catch (PokemonApiException ex) {
            throw ex;
        }
        
    }

    public void resetGuessedPokemonList()
    {
        generatedPokemonIds.clear();
    }

    public void resetGeneration() {
        max = 0;
        min = 0;
        range = 0;
    }

    public Set<Integer> getGeneratedPokemonIds() {
        return generatedPokemonIds;
    }

    public void setGeneratedPokemonIds(Set<Integer> generatedPokemonIds) {
        this.generatedPokemonIds = generatedPokemonIds;
    }
}
