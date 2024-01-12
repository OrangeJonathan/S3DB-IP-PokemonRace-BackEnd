package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.dto.PokemonDTO;
import dev.pokemonracer.mapper.PokemonMapper;
import dev.pokemonracer.model.Pokemon;
import dev.pokemonracer.serviceInterfaces.IPokeAPIService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/guess")
public class GuessPokemonController {

    private IPokeAPIService pokeAPIservice;
    private PokemonMapper mapper;
    private Pokemon pokemon;

    public GuessPokemonController(IPokeAPIService pokeAPIService) {
        this.pokeAPIservice = pokeAPIService;
        this.mapper = new PokemonMapper(pokeAPIservice);
    }

    // Get a random pokemon from the PokeAPI.
    @GetMapping("/pokemon")
    public PokemonDTO getRandomPokemon(@RequestParam("generation") int generation) {
        pokemon = pokeAPIservice.getPokemonWithId(pokeAPIservice.generateRandomPokemonId(generation));
        return mapper.toPokemonDTO(pokemon);
        
    }

    // Check if the user's guess is correct.
    @GetMapping("/iscorrect/{name}")
    public boolean guessPokemon(@PathVariable String name) throws JsonProcessingException {
        String pokemonName = pokemon.getName().toLowerCase();
        if (name.equalsIgnoreCase("nidoran-f") || name.equalsIgnoreCase("nidoran-m"))
        {
            name = name.toLowerCase().replace("nidoran-f", "nidoran").replace("nidoran-m", "nidoran");
        }
        name = name.toLowerCase().replace(" ", "-"); 
        return pokemonName.equals(name);
    }

    // Reset the PokeAPIService to be ready for a new game.
    @PostMapping("/pokemon/reset")
    public void resetPokemon() {
        pokeAPIservice.resetGuessedPokemonList();
        pokeAPIservice.resetGeneration();
    }

}
