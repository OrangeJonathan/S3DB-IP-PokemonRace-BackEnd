package dev.pokemonracer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pokemonracer.model.Pokemon;

@Service
public class PokeAPIservice {
 
    private final String pokeAPI = "https://pokeapi.co/api/v2/pokemon/";
    private final RestTemplate restTemplate;
    private Set<Integer> generatedPokemonIds = new HashSet<>(); 
    
    public PokeAPIservice(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    private int generateRandomPokemonId() {
        int id;
        do {
            id = (int) (Math.random() * 1010) + 1;
        } while (generatedPokemonIds.contains(id)); 
        return id;
    }

    

    public Pokemon getRandomPokemon() throws JsonMappingException, JsonProcessingException {
        int id = generateRandomPokemonId();
        ResponseEntity<String> response = restTemplate.getForEntity(pokeAPI + id, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.getBody());

        int pokemonId = rootNode.get("id").asInt();
        String pokemonName = rootNode.get("species").get("name").asText();
        String pokemonImageUrl = rootNode.get("sprites").get("front_default").asText();
        String pokemonType = rootNode.get("types").get(0).get("type").get("name").asText();
        
        Pokemon pokemon = new Pokemon(pokemonId, pokemonName, pokemonType, pokemonImageUrl);
        generatedPokemonIds.add(pokemonId);
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
