package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.model.Pokemon;
import dev.pokemonracer.service.PokeAPIservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/guess")
public class GuessPokemonController {

    @Autowired
    private PokeAPIservice pokeAPIservice;

    Pokemon pokemon;

    @GetMapping("/pokemon")
    public Pokemon getRandomPokemon() throws JsonMappingException, JsonProcessingException {
        pokemon = pokeAPIservice.getRandomPokemon();
        return pokemon;
    }

    @GetMapping("/pokemon/{name}")
    public boolean guessPokemon(@PathVariable String name) throws JsonMappingException, JsonProcessingException {
        String pokemonName = pokemon.getName().toLowerCase();
        name = name.toLowerCase().replace(" ", "-"); 
    
        return pokemonName.equals(name); 
    }

}
