package dev.pokemonracer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.pokemonracer.model.Race;
import dev.pokemonracer.model.RaceStatus;
import dev.pokemonracer.model.User;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {

    List<Race> findByPlayer1AndStatus(User player, RaceStatus pending);

    List<Race> findByPlayer2AndStatus(User player, RaceStatus completed);
    
}
