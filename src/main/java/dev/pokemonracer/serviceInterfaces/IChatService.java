package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.ChatMessage;

public interface IChatService {
    public void saveChat(ChatMessage chatMessage);
    public List<ChatMessage> getChatsBySenderAndReciever(Long senderId, Long recieverId); 
}
