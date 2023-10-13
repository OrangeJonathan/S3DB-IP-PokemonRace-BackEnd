package dev.pokemonracer.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;

import dev.pokemonracer.model.Pokemon;

@Repository
public class PokemonRepository implements IPokemonRepository {
    private final String pokeAPI = "https://pokeapi.co/api/v2/pokemon/";
    private final RestTemplate restTemplate;

    public PokemonRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pokemon getPokemonWithId(int id) throws JsonMappingException, JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(pokeAPI + id, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.getBody());

        int pokemonId = rootNode.get("id").asInt();
        String pokemonName = rootNode.get("species").get("name").asText();
        String pokemonImageUrl = rootNode.get("sprites").get("front_default").asText();
        String pokemonType = rootNode.get("types").get(0).get("type").get("name").asText();
        
        Pokemon pokemon = new Pokemon(pokemonId, pokemonName, pokemonType, pokemonImageUrl);
        return pokemon;
    }

}
