package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.User;

public interface IRaceService {
    public Race createRace(User player1, User player2, long generationId, long timeLimit);
    public void startRace(Race race);
    public void endRace(Race race);
    public List<Race> getPendingRaces(User player);
    public List<Race> getCompletedRaces(User player);
    public void acceptInvitation(Race race, User player2);
}
