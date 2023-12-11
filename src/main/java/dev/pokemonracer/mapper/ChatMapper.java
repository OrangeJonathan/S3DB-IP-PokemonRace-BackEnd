package dev.pokemonracer.mapper;

import dev.pokemonracer.DTOs.ChatMessageDTO;
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
        ChatMessageDTO msgDTO = new ChatMessageDTO(msg.getMessage(), userService.getUserById(msg.getSenderId().getId()).getAuth0Id(), userService.getUserById(msg.getRecepientId().getId()).getAuth0Id(), msg.getTimeSent());
        return msgDTO;
    }

    public ChatMessage toChatMessage(ChatMessageDTO msgDTO) {
        ChatMessage msg = new ChatMessage(userService.getUserByAuth0Id(msgDTO.getSender_id()), userService.getUserByAuth0Id(msgDTO.getRecipientId()), msgDTO.getContent(), msgDTO.getTimeSent());
        return msg;
    }
}
    
