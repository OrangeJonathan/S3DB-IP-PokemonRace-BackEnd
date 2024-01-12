package dev.pokemonracer.repositoryInterfaces;

import dev.pokemonracer.model.Pokemon;

public interface IPokemonRepository {
    public Pokemon getPokemonWithId(int id);
}
