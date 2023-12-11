package dev.pokemonracer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.pokemonracer.model.ChatMessage;
import dev.pokemonracer.model.User;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
    List<ChatMessage> findBysenderIdAndRecepientId(User sender_Id, User recepient_Id);
}
