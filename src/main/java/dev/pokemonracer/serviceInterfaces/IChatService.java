package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.ChatMessage;

public interface IChatService {
    public void SaveChat(ChatMessage chatMessage);
    public List<ChatMessage> GetChatsBySenderAndReciever(Long senderId, Long recieverId); 
}
