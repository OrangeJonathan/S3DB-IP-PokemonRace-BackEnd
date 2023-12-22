package dev.pokemonracer.UnitTests;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.User_Friend;
import dev.pokemonracer.repository.FriendRepository;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.FriendService;
import dev.pokemonracer.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    void GetAcceptedFriendsByAuth0Id_ValidAuth0Id_ShouldReturnFriends() {
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
    void GetPendingFriendsByAuth0Id_ValidAuth0Id_ShouldReturnFriends() {
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
    void SendFriendRequest_ValidIds_FriendShouldBeSaved() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_email = "valid_receiver_email";
        User sender = new User();
        User receiver = new User();
        when(userService.getUserByAuth0Id(sender_auth0Id)).thenReturn(sender);
        when(userService.getUserByEmail(receiver_email)).thenReturn(receiver);
        when(friendService.getUserFriend(sender_auth0Id, receiver.getAuth0Id())).thenReturn(null);

        // Act
        friendService.SendFriendRequest(sender_auth0Id, receiver_email);

        // Assert
        verify(friendRepository).save(any(User_Friend.class));
    }   

    @Test
    void SendFriendRequest_FriendsExist_FriendShouldNotBeSaved() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_email = "valid_receiver_email";
        User sender = new User();
        User receiver = new User();
        User_Friend userFriend = new User_Friend();
        when(userService.getUserByAuth0Id(sender_auth0Id)).thenReturn(sender);
        when(userService.getUserByEmail(receiver_email)).thenReturn(receiver);
        when(friendService.getUserFriend(sender_auth0Id, receiver.getAuth0Id())).thenReturn(userFriend);

        // Act
        friendService.SendFriendRequest(sender_auth0Id, receiver_email);

        // Assert
        verify(friendRepository, never()).save(userFriend);
    }   

    @Test
    void AcceptFriendRequest_ValidIds_ShouldNotThrowException() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        User_Friend userFriend = new User_Friend();
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(userFriend);

        // Act
        friendService.AcceptFriendRequest(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository).save(any(User_Friend.class));
        Assertions.assertEquals(userFriend.isAccepted(), true);
    }

    @Test
    void AcceptFriendRequest_FriendDoesNotExist_FriendsNotAccepted() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(null);

        // Act
        friendService.AcceptFriendRequest(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository, never()).save(any(User_Friend.class));
    }

    @Test
    void DeleteFriend_ValidIds_ShouldNotThrowException() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        User_Friend userFriend = new User_Friend();
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(userFriend);

        // Act
        friendService.DeleteFriend(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository).delete(any(User_Friend.class));
    }

    @Test
    void DeleteFriend_UserFriendDoesNotExist_FriendNotDeleted() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(null);

        // Act
        friendService.DeleteFriend(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository, never()).delete(any(User_Friend.class));
    }

    @Test
    void getUser_friend_ValidIds_ShouldReturnUserFriend() {
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
        User_Friend result = friendService.getUserFriend(sender_auth0Id, receiver_auth0Id);

        // Assert
        assertEquals(userFriend, result);
    }

    @Test
    void GetFriendsByAuth0Id_ValidAuth0Id_ShouldReturnFriends() {
        // Arrange
        String auth0Id = "valid_auth0Id";
        User user = new User();
        when(userService.getUserByAuth0Id(auth0Id)).thenReturn(user);
        when(friendRepository.findByIdFriendAndIsAccepted(user, true)).thenReturn(new ArrayList<>());

        // Act
        List<User> result = friendService.getFriendsByAuth0Id(auth0Id, true);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetFriendsByAuth0Id_ForLoop() {
        // Arrange
        User user = new User();
        user.setId(1L);
        User friendUser = new User();
        friendUser.setId(2L);
        User_Friend userFriend = new User_Friend(user, friendUser, true);
        when(userService.getUserByAuth0Id(any())).thenReturn(user);
        when(friendRepository.findByIdFriendAndIsAccepted(any(), anyBoolean())).thenReturn(Arrays.asList(userFriend));

        // Act
        List<User> friends = friendService.getFriendsByAuth0Id("auth0id123", true);

        // Assert
        assertEquals(1, friends.size());
        assertEquals(friendUser, friends.get(0));
    }
}