package dev.pokemonracer.UnitTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.model.Generation;
import dev.pokemonracer.model.Pokemon;
import dev.pokemonracer.repositoryInterfaces.IPokemonRepository;
import dev.pokemonracer.service.PokeAPIservice;
import dev.pokemonracer.serviceInterfaces.IGenerationService;

class PokemonServiceUnitTest {

    @Mock
    private IPokemonRepository pokemonRepository;

    @Mock
    private IGenerationService generationService;

    @InjectMocks
    private PokeAPIservice pokeAPIservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void GenerateRandomPokemonId_PokemonIdShouldBeBetween1And151() {

        // Arrange
        Generation generation = new Generation();
        generation.setUpperLimit(151);
        generation.setLowerLimit(1);

        when(generationService.GetGeneration(1L)).thenReturn(generation);

        // Act
        int randomPokemonId = pokeAPIservice.generateRandomPokemonId(1);

        // Assert
        assertTrue(randomPokemonId >= 1 && randomPokemonId <= 151);
    }

    @Test
    public void testGenerateRandomPokemonId_WhileLoop() {
        // Arrange
        Generation generation = new Generation();
        generation.setUpperLimit(151);
        generation.setLowerLimit(1);

        when(generationService.GetGeneration(1L)).thenReturn(generation);
        Set<Integer> ids = new HashSet<>();
        for (int i = 1; i <= 100; i++) {
            ids.add(i);
        }
        pokeAPIservice.setGeneratedPokemonIds(ids);

        // Act
        int id = pokeAPIservice.generateRandomPokemonId(1);

        // Assert
        assertTrue(id > 100 && id <= 151);
    }

    @Test
    void GetPokemonWithId_ShouldReturnPokemonWithId() throws JsonMappingException, JsonProcessingException {
        
        // Arrange
        int pokemonId = 5;
        Pokemon pokemon = new Pokemon();
        when(pokemonRepository.getPokemonWithId(pokemonId)).thenReturn(pokemon);

        // Act
        Pokemon retrievedPokemon = pokeAPIservice.getPokemonWithId(pokemonId);

        // Assert
        assertEquals(pokemon, retrievedPokemon);
        assertTrue(pokeAPIservice.getGeneratedPokemonIds().contains(pokemonId));
    }

    @Test
    void ResetGuessedPokemonList_ListShouldBeEmpty() {
        
        // Arrange
        pokeAPIservice.getGeneratedPokemonIds().add(1);

        // Act
        pokeAPIservice.resetGuessedPokemonList();

        // Assert
        assertTrue(pokeAPIservice.getGeneratedPokemonIds().isEmpty());
    }

    @Test
    void ResetGeneration_MaxMinRangeShouldBeZero() {

        // Arrange
        pokeAPIservice.setMax(151);
        pokeAPIservice.setMin(1);
        pokeAPIservice.setRange(150);

        // Act
        pokeAPIservice.resetGeneration();

        // Assert
        assertEquals(0, pokeAPIservice.getMax());
        assertEquals(0, pokeAPIservice.getMin());
        assertEquals(0, pokeAPIservice.getRange());
    }
}