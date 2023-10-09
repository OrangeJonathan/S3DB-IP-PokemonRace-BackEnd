package dev.pokemonracer.service;

import org.springframework.http.HttpStatus;
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

    public PokeAPIservice(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    private int generateRandomPokemonId()
    {
        return (int) (Math.random() * 1010) + 1;
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
        System.out.println(pokemonType);
        System.out.println(pokemonName);
        System.out.println(pokemonImageUrl);
        System.out.println(pokemonId);

        return pokemon;
    }
    
}