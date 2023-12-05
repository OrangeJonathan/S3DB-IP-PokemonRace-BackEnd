package dev.pokemonracer.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import dev.pokemonracer.DTOs.ChatMessageDTO;

@Controller
public class ChatController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ChatMessageDTO greeting(ChatMessageDTO message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ChatMessageDTO(message.getContent());
     }
}
