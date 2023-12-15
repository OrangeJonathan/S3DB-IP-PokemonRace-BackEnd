package dev.pokemonracer.serviceInterfaces;

import java.util.Set;
import dev.pokemonracer.model.Pokemon;

public interface IPokeAPIService {
    public int generateRandomPokemonId(int generationNumber);
    public Pokemon getPokemonWithId(int id);
    public void resetGuessedPokemonList();
    public Set<Integer> getGeneratedPokemonIds();
    public void setGeneratedPokemonIds(Set<Integer> generatedPokemonIds);
    public void resetGeneration();
}
