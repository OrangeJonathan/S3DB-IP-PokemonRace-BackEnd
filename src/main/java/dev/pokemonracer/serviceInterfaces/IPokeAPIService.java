package dev.pokemonracer.serviceInterfaces;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.model.Pokemon;

public interface IPokeAPIService {
    public int generateRandomPokemonId(int generationNumber);
    public Pokemon getPokemonWithId(int id) throws JsonMappingException, JsonProcessingException;
    public void resetGuessedPokemonList();
    public Set<Integer> getGeneratedPokemonIds();
    public void setGeneratedPokemonIds(Set<Integer> generatedPokemonIds);
    public void resetGeneration();
}
