package dev.pokemonracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.pokemonracer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
