package dev.pokemonracer.serviceInterfaces;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.DTOs.PokemonDTO;

public interface IPokeAPIService {
    public void setPokemonGeneration(int generation);
    public int getPokemonGeneration() ;
    public int generateRandomPokemonId(int generationNumber);
    public PokemonDTO getPokemonWithId(int id) throws JsonMappingException, JsonProcessingException;
    public void resetGuessedPokemonList();
    public Set<Integer> getGeneratedPokemonIds();
    public void setGeneratedPokemonIds(Set<Integer> generatedPokemonIds);
}
