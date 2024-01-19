package dev.pokemonracer.mapper;

import dev.pokemonracer.dto.ChatMessageDTO;
import dev.pokemonracer.model.ChatMessage;
import dev.pokemonracer.serviceInterfaces.IUserService;

import org.springframework.stereotype.Component;

@Component
public class ChatMapper {

    IUserService userService;

    public ChatMapper(IUserService userService) {
        this.userService = userService;
    }

    public ChatMessageDTO toChatMessageDTO(ChatMessage msg) {
        return new ChatMessageDTO(msg.getMessage(), userService.getUserById(msg.getSenderId().getId()).getAuth0Id(), userService.getUserById(msg.getRecepientId().getId()).getAuth0Id(), msg.getTimeSent());
    }

    public ChatMessage toChatMessage(ChatMessageDTO msgDTO) {
        return new ChatMessage(userService.getUserByAuth0Id(msgDTO.getSenderId()), userService.getUserByAuth0Id(msgDTO.getRecipientId()), msgDTO.getContent(), msgDTO.getTimeSent());
    }
}
    
