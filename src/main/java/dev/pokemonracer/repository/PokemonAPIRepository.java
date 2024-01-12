package dev.pokemonracer.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;
import dev.pokemonracer.exceptions.PokemonApiException;
import dev.pokemonracer.exceptions.PokemonNotFoundException;
import dev.pokemonracer.model.Pokemon;

@Repository
public class PokemonAPIRepository implements IPokemonRepository {
    private static final String pokeAPI = "https://pokeapi.co/api/v2/pokemon/";
    private final RestTemplate restTemplate;

    public PokemonAPIRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pokemon getPokemonWithId(int id) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(pokeAPI + id, String.class);
    
            if (response.getStatusCode().is2xxSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getBody());
    
                int pokemonId = rootNode.get("id").asInt();
                String pokemonName = rootNode.get("species").get("name").asText();
                String pokemonImageUrl = rootNode.get("sprites").get("front_default").asText();
                String pokemonType = rootNode.get("types").get(0).get("type").get("name").asText();
    
                return new Pokemon(pokemonId, pokemonName, pokemonType, pokemonImageUrl);
            } else {
                throw new PokemonApiException("Failed to retrieve Pokemon with ID: " + id +
                        ". HTTP Status Code: " + response.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            throw new PokemonApiException("Failed to parse JSON response for Pokemon with ID: " + id, e);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new PokemonNotFoundException("Pokemon with ID: " + id + " does not exist", ex);
            }
            throw new PokemonApiException("HTTP error while retrieving Pokemon with ID: " + id, ex);
        } catch (Exception e) {
            // Handle other unexpected errors
            throw new PokemonApiException("Unexpected error while retrieving Pokemon with ID: " + id, e);
        }
    }
        
}
