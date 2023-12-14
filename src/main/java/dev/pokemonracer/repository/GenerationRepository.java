package dev.pokemonracer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.pokemonracer.model.Generation;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long>{
    Optional<Generation> findById(Long Id);
}
