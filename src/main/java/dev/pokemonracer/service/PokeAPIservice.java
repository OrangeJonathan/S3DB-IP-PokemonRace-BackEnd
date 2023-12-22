package dev.pokemonracer.service;

import java.util.Set;
import java.security.SecureRandom;
import java.util.HashSet;
import org.springframework.stereotype.Service;

import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;
import dev.pokemonracer.serviceInterfaces.IGenerationService;
import dev.pokemonracer.serviceInterfaces.IPokeAPIService;
import lombok.Getter;
import lombok.Setter;
import dev.pokemonracer.exceptions.PokemonApiException;
import dev.pokemonracer.model.Generation;
import dev.pokemonracer.model.Pokemon;

@Getter
@Setter
@Service
public class PokeAPIservice implements IPokeAPIService {
 
    private Set<Integer> generatedPokemonIds = new HashSet<>(); 

    private int max;
    private int min;
    private int range;
    private SecureRandom secureRandom;

    private IPokemonRepository pokemonRepository;
    private IGenerationService generationService;

    public PokeAPIservice(IPokemonRepository pokemonRepository, IGenerationService generationService) {
        this.pokemonRepository = pokemonRepository;
        this.generationService = generationService;
        this.secureRandom = new SecureRandom();
    }

    private void setGeneration(int id) {
        Generation generation = generationService.GetGeneration(Long.valueOf(id));
        max = generation.getUpperLimit();
        min = generation.getLowerLimit();
        range = max - min;
    }

    public int generateRandomPokemonId(int generationNumber) {
        int id;
        setGeneration(generationNumber);
        do {
            id = secureRandom.nextInt(range) + min;
        } while (generatedPokemonIds.contains(id));
        return id;
    }

    public Pokemon getPokemonWithId(int id) {
        try {
            generatedPokemonIds.add(id);
            return pokemonRepository.getPokemonWithId(id);
        } catch (PokemonApiException ex) {
            throw new PokemonApiException(ex.getMessage());
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
