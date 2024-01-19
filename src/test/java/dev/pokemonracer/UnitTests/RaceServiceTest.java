package dev.pokemonracer.UnitTests;

import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.RaceRepository;
import dev.pokemonracer.service.RaceService;
import dev.pokemonracer.serviceInterfaces.IGenerationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static dev.pokemonracer.model.RaceStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private IGenerationService generationService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private RaceService raceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRace() {
        // Arrange
        User player1 = new User();
        User player2 = new User();
        long generationId = 1L;
        long timeLimit = 1000L;

        Race race = new Race();
        race.setPlayer1(player1);
        race.setPlayer2(player2);
        race.setGeneration(generationService.getGeneration(generationId));
        race.setTimeLimit(timeLimit);
        race.setStatus(PENDING);

        when(raceRepository.save(any(Race.class))).thenReturn(race);

        // Act
        Race result = raceService.createRace(player1, player2, generationId, timeLimit);

        // Assert
        assertEquals(race, result);
        verify(raceRepository, times(1)).save(any(Race.class));
    }

    @Test
    void testStartRace() {
        // Arrange
        Race race = new Race();
        race.setStatus(PENDING);

        // Act
        raceService.startRace(race);

        // Assert
        assertEquals(IN_PROGRESS, race.getStatus());
        verify(raceRepository, times(1)).save(race);
    }

    @Test
    void testEndRace() {
        // Arrange
        Race race = new Race();
        race.setStatus(IN_PROGRESS);

        // Act
        raceService.endRace(race);

        // Assert
        assertEquals(COMPLETED, race.getStatus());
        verify(raceRepository, times(1)).save(race);
    }

    @Test
    void testGetPendingRaces() {
        // Arrange
        User player = new User();
        Race race = new Race();
        race.setStatus(PENDING);

        when(raceRepository.findByPlayer2AndStatus(player, PENDING)).thenReturn(Arrays.asList(race));

        // Act
        List<Race> result = raceService.getPendingRaces(player);

        // Assert
        assertEquals(1, result.size());
        assertEquals(race, result.get(0));
    }

    @Test
    void testGetCompletedRaces() {
        // Arrange
        User player = new User();
        Race race1 = new Race();
        race1.setStatus(COMPLETED);
        Race race2 = new Race();
        race2.setStatus(COMPLETED);

        when(raceRepository.findByPlayer1AndStatus(player, COMPLETED)).thenReturn(Arrays.asList(race1));
        when(raceRepository.findByPlayer2AndStatus(player, COMPLETED)).thenReturn(Arrays.asList(race2));

        // Act
        List<Race> result = raceService.getCompletedRaces(player);

        // Assert
        assertEquals(2, result.size());
        assertEquals(race1, result.get(0));
        assertEquals(race2, result.get(1));
    }
}