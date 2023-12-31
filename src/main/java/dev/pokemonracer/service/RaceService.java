package dev.pokemonracer.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import static dev.pokemonracer.model.RaceStatus.*;
import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.RaceRepository;
import dev.pokemonracer.serviceInterfaces.IGenerationService;

@Service
public class RaceService {

    private RaceRepository raceRepository;
    private IGenerationService generationService;
    
    public RaceService(IGenerationService generationService, RaceRepository raceRepository) {
        this.generationService = generationService;
        this.raceRepository = raceRepository;
    }

    public Race CreateRace(User player1, User player2, long generationId, java.util.Date timeLimit) {
        Race race = new Race();
        race.setPlayer1(player1);
        race.setPlayer2(player2);
        race.setGeneration(generationService.GetGeneration(generationId));
        race.setTimeLimit(timeLimit);
        race.setStatus(PENDING);
        raceRepository.save(race);

        return race;
    }

    public void StartRace(Race race) {
        if (race.getStatus() != PENDING) return;
        race.setStatus(IN_PROGRESS);
        raceRepository.save(race);
    }

    public void EndRace(Race race) { 
        if (race.getStatus() != IN_PROGRESS) return;
        race.setStatus(COMPLETED);
        raceRepository.save(race);
    }

    public List<Race> GetPendingRaces(User player) {
        List<Race> races = raceRepository.findByPlayer2AndStatus(player, PENDING);
        return races;
    }

    public List<Race> GetCompletedRaces(User player) { 
        List<Race> racesAsPlayer1 = new ArrayList<>(raceRepository.findByPlayer1AndStatus(player, COMPLETED));
        List<Race> racesAsPlayer2 = new ArrayList<>(raceRepository.findByPlayer2AndStatus(player, COMPLETED));
        racesAsPlayer1.addAll(racesAsPlayer2);
        return racesAsPlayer1;
    }
}
