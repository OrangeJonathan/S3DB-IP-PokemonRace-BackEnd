package dev.pokemonracer.dto;

import java.util.List;

import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.RaceStatus;
import dev.pokemonracer.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RaceDTO {
    private User player1;
    private int player1Score;
    private User player2;
    private int player2Score;
    private RaceStatus status;
    private long generation;
    private long timeLimit;
}
