package dev.pokemonracer.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.pokemonracer.dto.RaceDTO;
import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.RaceStatus;
import dev.pokemonracer.model.User;

@Component
public class RaceMapper {
    
    public RaceStatus SringToStatus(String status){
        status = status.toUpperCase();
        switch(status){
            case "PENDING":
                return RaceStatus.PENDING;
            case "IN_PROGRESS":
                return RaceStatus.IN_PROGRESS;
            case "COMPLETED":
                return RaceStatus.COMPLETED;
            default:
                return null;
        }
    }

    public String StatusToString(RaceStatus Status){
        switch(Status){
            case PENDING:
                return "PENDING";
            case IN_PROGRESS:
                return "IN_PROGRESS";
            case COMPLETED:
                return "COMPLETED";
            default:
                return null;
        }
    }
}
