package dev.pokemonracer.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.pokemonracer.model.ChatMessage;
import dev.pokemonracer.repository.ChatMessageRepository;
import dev.pokemonracer.serviceInterfaces.IChatService;

@Service
public class ChatService implements IChatService{
    
    private ChatMessageRepository chatMessageRepository;
    private UserService userService;

    public ChatService(ChatMessageRepository chatMessageRepository, UserService userService) {
        this.chatMessageRepository = chatMessageRepository;
        this.userService = userService;
    }

    public void SaveChat(ChatMessage chatMessage) {
        try {
            chatMessage.setTimeSent(new java.util.Date());
            chatMessageRepository.save(chatMessage);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        
    }

    public List<ChatMessage> GetChatsBySenderAndReciever(Long senderId, Long recieverId) {
        try {
            if (senderId == null || recieverId == null ) throw new IllegalArgumentException("senderID or recieverID is null");
            List<ChatMessage> chats = chatMessageRepository.findBysenderIdAndRecepientId(userService.getUserById(senderId), userService.getUserById(recieverId));
            chats.addAll(chatMessageRepository.findBysenderIdAndRecepientId(userService.getUserById(recieverId), userService.getUserById(senderId)));
            List<ChatMessage> sortedChats = SortListByTimeSent(chats);
    
            return sortedChats;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private List<ChatMessage> SortListByTimeSent(List<ChatMessage> list) {
        Collections.sort(list);
        return list;
    }

    

}
