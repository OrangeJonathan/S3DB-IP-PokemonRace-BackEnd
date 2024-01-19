package dev.pokemonracer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.dto.RaceDTO;
import dev.pokemonracer.mapper.RaceMapper;
import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.RaceStatus;
import dev.pokemonracer.model.User;
import dev.pokemonracer.serviceInterfaces.IRaceService;
import dev.pokemonracer.serviceInterfaces.IUserService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/race")
public class RaceController {

    IRaceService raceService;
    IUserService userService;
    RaceMapper raceMapper;
    private Race currentRace;

    private Map<String, Integer> scores = new HashMap<>();

    private final SimpMessagingTemplate messagingTemplate;

    public RaceController(IRaceService raceService, IUserService userService, RaceMapper raceMapper, SimpMessagingTemplate messagingTemplate) {
        this.raceService = raceService;
        this.userService = userService;
        this.raceMapper = raceMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("")
    public List<Race> getRaces(@RequestParam String auth0Id, @RequestParam String status) {
        RaceStatus raceStatus = raceMapper.SringToStatus(status);
        if (raceStatus == RaceStatus.PENDING){
            return raceService.getPendingRaces(userService.getUserByAuth0Id(auth0Id));
        }
        else if (raceStatus == RaceStatus.COMPLETED){
            return raceService.getCompletedRaces(userService.getUserByAuth0Id(auth0Id));
        }
        else {
            return null;
        }
    }

    @PostMapping("")
    public void inviteFriendToRace(@RequestParam String player1Auth0Id, @RequestParam String player2Auth0Id, @RequestParam long generation, @RequestParam long timeLimit) {
        currentRace = raceService.createRace(userService.getUserByAuth0Id(player1Auth0Id), userService.getUserByAuth0Id(player2Auth0Id), generation, timeLimit);
    }

    @PostMapping("/accept")
    public void acceptInvitation(@RequestParam String player2Auth0Id) {
        raceService.acceptInvitation(currentRace, userService.getUserByAuth0Id(player2Auth0Id));
    }
    
    @PostMapping("/submitScore")
    public void submitScore(@RequestParam String auth0Id, @RequestParam int score) {
        scores.put(auth0Id, score);

        // If both scores have been submitted, compare them
        if (scores.size() == 2) {
            String winnerAuth0Id = scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

            // Send a message to the clients to announce the winner
            messagingTemplate.convertAndSend("/topic/winner", userService.getUserByAuth0Id(winnerAuth0Id).getUsername());
        }
    }

}
