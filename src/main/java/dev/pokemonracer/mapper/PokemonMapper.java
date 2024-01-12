package dev.pokemonracer.mapper;

import org.springframework.stereotype.Component;

import dev.pokemonracer.dto.PokemonDTO;
import dev.pokemonracer.model.Pokemon;
import dev.pokemonracer.serviceInterfaces.IPokeAPIService;

@Component
public class PokemonMapper {
    
    IPokeAPIService pokeAPIService;

    public PokemonMapper(IPokeAPIService pokeAPIService) {
        this.pokeAPIService = pokeAPIService;
    }

    public PokemonDTO toPokemonDTO(Pokemon pokemon) {
        return new PokemonDTO(pokemon.getId(), pokemon.getName(), pokemon.getImageString());
    }

    public Pokemon toPokemon(PokemonDTO pokemonDTO) {
        return new Pokemon(pokemonDTO.getId(), pokemonDTO.getName(), pokemonDTO.getImageString());
    }
}
