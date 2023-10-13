package dev.pokemonracer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;

import dev.pokemonracer.model.Pokemon;

@Service
public class PokeAPIservice {
 
    private Set<Integer> generatedPokemonIds = new HashSet<>(); 

    @Autowired
    private IPokemonRepository pokemonRepository;

    public int generateRandomPokemonId() {
        int id;
        do {
            id = (int) (Math.random() * 1010) + 1;
        } while (generatedPokemonIds.contains(id)); 
        return id;
    }

    public Pokemon getPokemonWithId(int id) throws JsonMappingException, JsonProcessingException {
        generatedPokemonIds.add(id);
        var pokemon = pokemonRepository.getPokemonWithId(id);
        System.out.println("pokemon: " + pokemon.getName());
        return pokemon; 
    }

    public void resetGuessedPokemonList()
    {
        generatedPokemonIds.clear();
        System.out.println("reset");
    }


    public Set<Integer> getGeneratedPokemonIds() {
        return generatedPokemonIds;
    }

    public void setGeneratedPokemonIds(Set<Integer> generatedPokemonIds) {
        this.generatedPokemonIds = generatedPokemonIds;
    }
}
