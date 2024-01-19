package dev.pokemonracer.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static dev.pokemonracer.model.RaceStatus.*;
import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.RaceRepository;
import dev.pokemonracer.serviceInterfaces.IGenerationService;
import dev.pokemonracer.serviceInterfaces.IRaceService;

@Service
public class RaceService implements IRaceService {

    private RaceRepository raceRepository;
    private IGenerationService generationService;
    private final SimpMessagingTemplate messagingTemplate;

    public RaceService(IGenerationService generationService, RaceRepository raceRepository, SimpMessagingTemplate messagingTemplate) {
        this.generationService = generationService;
        this.raceRepository = raceRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public Race createRace(User player1, User player2, long generationId, long timeLimit) {
        Race race = new Race();
        race.setPlayer1(player1);
        race.setPlayer2(player2);
        race.setGeneration(generationService.getGeneration(generationId));
        race.setTimeLimit(timeLimit);
        race.setStatus(PENDING);
        raceRepository.save(race);

        return race;
    }

    public void acceptInvitation(Race race, User player2) {
        System.out.println("Accepting invitation " + race.getPlayer2().getId().equals(player2.getId()));
        if (race.getStatus() != PENDING || !race.getPlayer2().getId().equals(player2.getId())) return;
        
        // Start the race
        System.out.println("Race Starting");
        startRace(race);
    }

    public void startRace(Race race) {
        if (race.getStatus() != PENDING) return;
        race.setStatus(IN_PROGRESS);
        raceRepository.save(race);

        // Send a countdown message to the clients
        for (int i = 4; i >= 0; i--) {
            messagingTemplate.convertAndSend("/topic/countdown", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        messagingTemplate.convertAndSend("/topic/start", "Game started!");
    }

    public void endRace(Race race) { 
        if (race.getStatus() != IN_PROGRESS) return;
        race.setStatus(COMPLETED);
        raceRepository.save(race);
    }
    

    public List<Race> getPendingRaces(User player) {
        return raceRepository.findByPlayer2AndStatus(player, PENDING);
    }

    public List<Race> getCompletedRaces(User player) { 
        List<Race> racesAsPlayer1 = new ArrayList<>(raceRepository.findByPlayer1AndStatus(player, COMPLETED));
        List<Race> racesAsPlayer2 = new ArrayList<>(raceRepository.findByPlayer2AndStatus(player, COMPLETED));
        racesAsPlayer1.addAll(racesAsPlayer2);
        return racesAsPlayer1;
    }
}
