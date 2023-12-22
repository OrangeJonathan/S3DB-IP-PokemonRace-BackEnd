package dev.pokemonracer.UnitTests;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.User_Friend;
import dev.pokemonracer.repository.FriendRepository;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.FriendService;
import dev.pokemonracer.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class FriendServiceTest {

    private FriendService friendService;
    private UserService userService;
    private FriendRepository friendRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        friendRepository = mock(FriendRepository.class);
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
        friendService = new FriendService(friendRepository, userService);
    }

    @Test
    public void GetAcceptedFriendsByAuth0Id_ValidAuth0Id_ShouldReturnFriends() {
        // Arrange
        String auth0Id = "valid_auth0Id";
        User user = new User();
        when(userService.getUserByAuth0Id(auth0Id)).thenReturn(user);
        when(friendRepository.findByIdFriendAndIsAccepted(user, true)).thenReturn(new ArrayList<>());

        // Act
        List<User> result = friendService.GetAcceptedFriendsByAuth0Id(auth0Id);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void GetPendingFriendsByAuth0Id_ValidAuth0Id_ShouldReturnFriends() {
        // Arrange
        String auth0Id = "valid_auth0Id";
        User user = new User();
        when(userService.getUserByAuth0Id(auth0Id)).thenReturn(user);
        when(friendRepository.findByIdFriendAndIsAccepted(user, false)).thenReturn(new ArrayList<>());

        // Act
        List<User> result = friendService.GetPendingFriendsByAuth0Id(auth0Id);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void SendFriendRequest_ValidIds_ShouldNotThrowException() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_email = "valid_receiver_email";
        User sender = new User();
        User receiver = new User();
        when(userService.getUserByAuth0Id(sender_auth0Id)).thenReturn(sender);
        when(userService.getUserByEmail(receiver_email)).thenReturn(receiver);
        when(friendService.getUser_friend(sender_auth0Id, receiver.getAuth0Id())).thenReturn(null);

        // Act & Assert
        assertDoesNotThrow(() -> friendService.SendFriendRequest(sender_auth0Id, receiver_email));
    }

    @Test
    public void AcceptFriendRequest_ValidIds_ShouldNotThrowException() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        User_Friend userFriend = new User_Friend();
        when(friendService.getUser_friend(sender_auth0Id, receiver_auth0Id)).thenReturn(userFriend);

        // Act & Assert
        assertDoesNotThrow(() -> friendService.AcceptFriendRequest(sender_auth0Id, receiver_auth0Id));
    }

    @Test
    public void DeleteFriend_ValidIds_ShouldNotThrowException() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        User_Friend userFriend = new User_Friend();
        when(friendService.getUser_friend(sender_auth0Id, receiver_auth0Id)).thenReturn(userFriend);

        // Act & Assert
        assertDoesNotThrow(() -> friendService.DeleteFriend(sender_auth0Id, receiver_auth0Id));
    }

    @Test
    public void getUser_friend_ValidIds_ShouldReturnUserFriend() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        User sender = new User();
        User receiver = new User();
        User_Friend userFriend = new User_Friend();
        when(userService.getUserByAuth0Id(sender_auth0Id)).thenReturn(sender);
        when(userService.getUserByAuth0Id(receiver_auth0Id)).thenReturn(receiver);
        when(friendRepository.findByIdUserAndIdFriend(sender, receiver)).thenReturn(userFriend);

        // Act
        User_Friend result = friendService.getUser_friend(sender_auth0Id, receiver_auth0Id);

        // Assert
        assertEquals(userFriend, result);
    }

    @Test
    public void GetFriendsByAuth0Id_ValidAuth0Id_ShouldReturnFriends() {
        // Arrange
        String auth0Id = "valid_auth0Id";
        User user = new User();
        when(userService.getUserByAuth0Id(auth0Id)).thenReturn(user);
        when(friendRepository.findByIdFriendAndIsAccepted(user, true)).thenReturn(new ArrayList<>());

        // Act
        List<User> result = friendService.GetFriendsByAuth0Id(auth0Id, true);

        // Assert
        assertNotNull(result);
    }
}