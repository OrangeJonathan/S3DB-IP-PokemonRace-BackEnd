package dev.pokemonracer.repositoryInterfaces;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.model.Pokemon;

public interface IPokemonRepository {
    public Pokemon getPokemonWithId(int id);
}
