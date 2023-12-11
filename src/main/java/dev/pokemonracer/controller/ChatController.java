package dev.pokemonracer.controller;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.DTOs.ChatMessageDTO;
import dev.pokemonracer.mapper.ChatMapper;
import dev.pokemonracer.model.ChatMessage;
import dev.pokemonracer.serviceInterfaces.IChatService;
import dev.pokemonracer.serviceInterfaces.IUserService;

@RestController
@RequestMapping("api/chat")
public class ChatController {

    private ChatMapper mapper;
    private IChatService chatService;
    private IUserService userService;

    public ChatController(IUserService userService, IChatService chatService) {
        this.mapper = new ChatMapper(userService);
        this.chatService = chatService;
        this.userService = userService;
    }

    @MessageMapping("/hello/{recipientId}")
    @SendTo("/topic/private/{recipientId}")
    public ChatMessageDTO greeting(@DestinationVariable String recipientId, ChatMessageDTO message) throws Exception {
        Thread.sleep(100); // simulated delay
        chatService.SaveChat(mapper.toChatMessage(message));
        return message;
    }



    @GetMapping("")
    public List<ChatMessageDTO> getMessages(@RequestParam String senderId,@RequestParam String receiverId) throws Exception{
        List<ChatMessageDTO> messages = new ArrayList<>();
        List<ChatMessage> chats = chatService.GetChatsBySenderAndReciever(userService.getUserByAuth0Id(senderId).getId(), userService.getUserByAuth0Id(receiverId).getId());
        for (ChatMessage chatMessage : chats) {
            messages.add(mapper.toChatMessageDTO(chatMessage));
        }
        return messages;
    }
}
