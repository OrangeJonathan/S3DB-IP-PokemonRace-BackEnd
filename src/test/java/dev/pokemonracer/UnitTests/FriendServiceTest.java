package dev.pokemonracer.UnitTests;

import dev.pokemonracer.model.FriendID;
import dev.pokemonracer.model.User;
import dev.pokemonracer.model.UserFriend;
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

class FriendServiceTest {

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
        List<User> result = friendService.getAcceptedFriendsByAuth0Id(auth0Id);

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
        List<User> result = friendService.getPendingFriendsByAuth0Id(auth0Id);

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
        friendService.sendFriendRequest(sender_auth0Id, receiver_email);

        // Assert
        verify(friendRepository).save(any(UserFriend.class));
    }   

    @Test
    void SendFriendRequest_FriendsExist_FriendShouldNotBeSaved() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_email = "valid_receiver_email";
        User sender = new User();
        User receiver = new User();
        UserFriend userFriend = new UserFriend();
        when(userService.getUserByAuth0Id(sender_auth0Id)).thenReturn(sender);
        when(userService.getUserByEmail(receiver_email)).thenReturn(receiver);
        when(friendService.getUserFriend(sender_auth0Id, receiver.getAuth0Id())).thenReturn(userFriend);

        // Act
        friendService.sendFriendRequest(sender_auth0Id, receiver_email);

        // Assert
        verify(friendRepository, never()).save(userFriend);
    }   

    @Test
    void AcceptFriendRequest_ValidIds_ShouldNotThrowException() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        UserFriend userFriend = new UserFriend();
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(userFriend);

        // Act
        friendService.acceptFriendRequest(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository).save(any(UserFriend.class));
        Assertions.assertEquals(true, userFriend.isAccepted());
    }

    @Test
    void AcceptFriendRequest_FriendDoesNotExist_FriendsNotAccepted() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(null);

        // Act
        friendService.acceptFriendRequest(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository, never()).save(any(UserFriend.class));
    }

    @Test
    void DeleteFriend_ValidIds_ShouldNotThrowException() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        UserFriend userFriend = new UserFriend();
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(userFriend);

        // Act
        friendService.deleteFriend(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository).delete(any(UserFriend.class));
    }

    @Test
    void DeleteFriend_UserFriendDoesNotExist_FriendNotDeleted() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        when(friendService.getUserFriend(sender_auth0Id, receiver_auth0Id)).thenReturn(null);

        // Act
        friendService.deleteFriend(sender_auth0Id, receiver_auth0Id);

        // Assert
        verify(friendRepository, never()).delete(any(UserFriend.class));
    }

    @Test
    void getUser_friend_ValidIds_ShouldReturnUserFriend() {
        // Arrange
        String sender_auth0Id = "valid_sender_auth0Id";
        String receiver_auth0Id = "valid_receiver_auth0Id";
        User sender = new User();
        User receiver = new User();
        UserFriend userFriend = new UserFriend();
        when(userService.getUserByAuth0Id(sender_auth0Id)).thenReturn(sender);
        when(userService.getUserByAuth0Id(receiver_auth0Id)).thenReturn(receiver);
        when(friendRepository.findByIdUserAndIdFriend(sender, receiver)).thenReturn(userFriend);

        // Act
        UserFriend result = friendService.getUserFriend(sender_auth0Id, receiver_auth0Id);

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
    void GetFriendsByAuth0Id_ForEveryUserFriend_ReturnFriends() {
        // Arrange
        User user = new User();
        user.setId(1L);
        User friendUser = new User();
        friendUser.setId(2L);
        UserFriend userFriend = new UserFriend(user, friendUser, true);
        when(userService.getUserByAuth0Id(any())).thenReturn(user);
        when(friendRepository.findByIdFriendAndIsAccepted(any(), anyBoolean())).thenReturn(Arrays.asList(userFriend));

        // Act
        List<User> friends = friendService.getFriendsByAuth0Id("auth0id123", true);

        // Assert
        assertEquals(1, friends.size());
        assertEquals(friendUser, friends.get(0));
    }

    @Test
    void GetFriendsByAuth0Id_IfUserId_UseFriendID() {
        // Arrange
        User user = new User();
        user.setId(1L);
        User friendUser = new User();
        friendUser.setId(2L);
        UserFriend userFriend = new UserFriend(user, friendUser, true);
        when(userService.getUserByAuth0Id(any())).thenReturn(user);
        when(friendRepository.findByIdFriendAndIsAccepted(any(), anyBoolean())).thenReturn(Arrays.asList(userFriend));

        // Act
        List<User> friends = friendService.getFriendsByAuth0Id("auth0id123", true);

        // Assert
        assertEquals(1, friends.size());
        assertEquals(friendUser, friends.get(0));

        // Arrange
        userFriend.setId(new FriendID(friendUser, user));
        when(friendRepository.findByIdFriendAndIsAccepted(any(), anyBoolean())).thenReturn(Arrays.asList(userFriend));

        // Act
        friends = friendService.getFriendsByAuth0Id("auth0id123", true);

        // Assert
        assertEquals(1, friends.size());
        assertEquals(friendUser, friends.get(0));
    }
}