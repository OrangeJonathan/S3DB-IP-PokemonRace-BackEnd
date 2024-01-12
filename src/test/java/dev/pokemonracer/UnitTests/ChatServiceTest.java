package dev.pokemonracer.UnitTests;

import dev.pokemonracer.model.ChatMessage;
import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.ChatMessageRepository;
import dev.pokemonracer.service.ChatService;
import dev.pokemonracer.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    private ChatService chatService;
    private ChatMessageRepository chatMessageRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        chatMessageRepository = mock(ChatMessageRepository.class);
        userService = mock(UserService.class);
        chatService = new ChatService(chatMessageRepository, userService);
    }

    @Test
    void SaveChat_ChatShouldBeInserted() {
        // Arrange
        ChatMessage chatMessage = new ChatMessage();
        when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(chatMessage);

        // Act
        chatService.saveChat(chatMessage);

        // Assert
        verify(chatMessageRepository, times(1)).save(chatMessage);
    }

    @Test
    void SaveChat_NullChat_ShouldThrowException() {
        // Arrange
        ChatMessage chatMessage = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> chatService.saveChat(chatMessage));
    }

    @Test
    void GetChatsBySenderAndReciever_ChatExists_ReturnsChats() {
        // Arrange
        User sender = new User();
        sender.setId(1L);
        User receiver = new User();
        receiver.setId(2L);

        ChatMessage chat1 = new ChatMessage();
        chat1.setTimeSent(new Date());
        ChatMessage chat2 = new ChatMessage();
        chat2.setTimeSent(new Date());

        when(userService.getUserById(sender.getId())).thenReturn(sender);
        when(userService.getUserById(receiver.getId())).thenReturn(receiver);
        when(chatMessageRepository.findBysenderIdAndRecepientId(sender, receiver)).thenReturn(new ArrayList<>(Arrays.asList(chat1)));
        when(chatMessageRepository.findBysenderIdAndRecepientId(receiver, sender)).thenReturn(new ArrayList<>(Arrays.asList(chat2)));

        // Act
        List<ChatMessage> result = chatService.getChatsBySenderAndReciever(sender.getId(), receiver.getId());

        // Assert
        assertEquals(2, result.size());
        verify(chatMessageRepository, times(1)).findBysenderIdAndRecepientId(sender, receiver);
        verify(chatMessageRepository, times(1)).findBysenderIdAndRecepientId(receiver, sender);
    }

    @Test
    void GetChatsBySenderAndReciever_NullSenderId_ShouldThrowException() {
        // Arrange
        Long senderId = null;
        Long receiverId = 2L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> chatService.getChatsBySenderAndReciever(senderId, receiverId));
    }

    @Test
    void GetChatsBySenderAndReciever_NullReceiverId_ShouldThrowException() {
        // Arrange
        Long senderId = 1L;
        Long receiverId = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> chatService.getChatsBySenderAndReciever(senderId, receiverId));
    }

    @Test
    void GetChatsBySenderAndReciever_NonExistingUsers_ShouldReturnEmptyList() {
        // Arrange
        Long senderId = 1L;
        Long receiverId = 2L;

        when(userService.getUserById(senderId)).thenReturn(null);
        when(userService.getUserById(receiverId)).thenReturn(null);

        // Act
        List<ChatMessage> result = chatService.getChatsBySenderAndReciever(senderId, receiverId);

        // Assert
        assertTrue(result.isEmpty());
    }
}