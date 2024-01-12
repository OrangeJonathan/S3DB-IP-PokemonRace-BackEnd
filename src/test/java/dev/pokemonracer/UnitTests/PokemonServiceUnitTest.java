package dev.pokemonracer.UnitTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.exceptions.PokemonApiException;
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

    @Spy
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

        when(generationService.getGeneration(1L)).thenReturn(generation);

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

        when(generationService.getGeneration(anyLong())).thenReturn(generation);

        Set<Integer> ids = new HashSet<>();
        ids.add(150);
        pokeAPIservice.setGeneratedPokemonIds(ids);

        SecureRandom secureRandom = org.mockito.Mockito.mock(SecureRandom.class);
        when(secureRandom.nextInt(anyInt())).thenReturn(150, 151);
        pokeAPIservice.setSecureRandom(secureRandom);

        // Act
        int id = pokeAPIservice.generateRandomPokemonId(1);

        // Assert
        assertEquals(151, id);
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

    @Test
    public void testGetPokemonWithId_Exception() {
        // Arrange
        when(pokemonRepository.getPokemonWithId(anyInt())).thenThrow(new PokemonApiException("Pokemon not found"));

        // Act & Assert
        Exception exception = assertThrows(PokemonApiException.class, () -> {
            pokeAPIservice.getPokemonWithId(1);
        });

        String expectedMessage = "Pokemon not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    
}