package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.DTOs.PokemonDTO;
import dev.pokemonracer.model.Pokemon;
import dev.pokemonracer.service.PokeAPIservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/guess")
public class GuessPokemonController {

    
    private PokeAPIservice pokeAPIservice;

    public GuessPokemonController(PokeAPIservice pokeAPIService) {
        this.pokeAPIservice = pokeAPIService;
    }

    PokemonDTO pokemon;

    @GetMapping("/pokemon")
    public PokemonDTO getRandomPokemon(@RequestParam("generation") int generation) throws JsonMappingException, JsonProcessingException {
        pokemon = pokeAPIservice.getPokemonWithId(pokeAPIservice.generateRandomPokemonId(generation));
        System.out.println(generation);
        return pokemon;
    }

    @GetMapping("/iscorrect/{name}")
    public boolean guessPokemon(@PathVariable String name) throws JsonMappingException, JsonProcessingException {
        String pokemonName = pokemon.getName().toLowerCase();
        name = name.toLowerCase().replace(" ", "-"); 
    
        return pokemonName.equals(name); 
    }

    @PostMapping("/pokemon/reset")
    public void resetPokemon() {
        pokeAPIservice.resetGuessedPokemonList();
    }

}
