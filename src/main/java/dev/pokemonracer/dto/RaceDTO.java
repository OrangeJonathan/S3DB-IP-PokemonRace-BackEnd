package dev.pokemonracer.dto;

import java.util.List;

import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.User;
import lombok.Data;

@Data
public class RaceDTO {
    
    private List<Race> races;
    private User player1;
}
