package dev.pokemonracer.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.pokemonracer.model.Generation;
import dev.pokemonracer.repository.GenerationRepository;
import dev.pokemonracer.service.GenerationService;

public class GenerationServiceTest {

    @Mock
    private GenerationRepository generationRepository;

    @InjectMocks
    private GenerationService generationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void InsertGeneration_DoesNotExist_GenerationInserted() {
        
        // Arrange
        Long id = 1L;
        int upperLimit = 151;
        int lowerLimit = 1;

        when(generationRepository.findById(id)).thenReturn(null);

        
        // Act
        generationService.InsertGeneration(id, upperLimit, lowerLimit);

        // Assert
        verify(generationRepository, times(1)).save(any(Generation.class));

    }

    @Test
    public void InsertGeneration_GenerationAlreadyExists_GenerationNotInserted() {
        
        // Arrange
        Long id = 1L;
        int upperLimit = 151;
        int lowerLimit = 1;

        when(generationRepository.findById(id)).thenReturn(Optional.of(new Generation()));
        
        // Act
        generationService.InsertGeneration(id, upperLimit, lowerLimit);
        
        // Assert
        verify(generationRepository, never()).save(any(Generation.class));
    }

    @Test
    public void GetGeneration_GenerationExists_ReturnsGeneration() {
        
        // Arrange
        Long id = 1L;
        Generation expectedGeneration = new Generation(id, 1, 151);

        when(generationRepository.findById(id)).thenReturn(Optional.of(expectedGeneration));

        // Act
        Generation result = generationService.GetGeneration(id);

        // Assert
        assertEquals(expectedGeneration, result);
    }

    @Test
    public void GetGeneration_GenerationDoesNotExist_ReturnsNull() {
        
        // Arrange
        Long id = 1L;

        when(generationRepository.findById(id)).thenReturn(null);

        // Act
        Generation result = generationService.GetGeneration(id);

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void GetAllGenerations_ReturnsAllGenerations() {
        
        // Arrange
        List<Generation> expectedGenerations = Arrays.asList(new Generation(1L, 1, 151), new Generation(2L, 152, 251));

        when(generationRepository.findAll()).thenReturn(expectedGenerations);

        // Act
        List<Generation> result = generationService.GetAllGenerations();

        // Assert
        assertEquals(expectedGenerations, result);
    }

    @Test
    public void UpdateGeneration_GenerationExists_UpdatesGeneration() {
        
        // Arrange
        Long id = 1L;
        int upperLimit = 153;
        int lowerLimit = 4;

        Generation existingGeneration = new Generation(id, 1, 151);

        when(generationRepository.findById(id)).thenReturn(Optional.of(existingGeneration));

        // Act
        generationService.UpdateGeneration(id, upperLimit, lowerLimit);

        // Assert
        verify(generationRepository, times(1)).save(any(Generation.class));

        assertEquals(upperLimit, existingGeneration.getUpperLimit());
        assertEquals(lowerLimit, existingGeneration.getLowerLimit());
    }

    @Test
    public void UpdateGeneration_GenerationDoesNotExist_Returns() {
        
        // Arrange
        Long id = 1L;
        int upperLimit = 153;
        int lowerLimit = 4;

        when(generationRepository.findById(id)).thenReturn(null);

        // Act
        generationService.UpdateGeneration(id, upperLimit, lowerLimit);

        // Assert
        verify(generationRepository, never()).save(any(Generation.class));
    }
}
