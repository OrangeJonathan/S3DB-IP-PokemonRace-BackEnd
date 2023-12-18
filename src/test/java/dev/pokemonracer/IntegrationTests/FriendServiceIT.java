package dev.pokemonracer.IntegrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.User_Friend;
import dev.pokemonracer.repository.FriendRepository;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.FriendService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FriendServiceIT {
    
    private FriendService friendService;
    private UserRepository userRepository;
    private FriendRepository friendRepository;

    @Autowired
    public FriendServiceIT(FriendService friendService, UserRepository userRepository, FriendRepository friendRepository) {
        this.friendService = friendService;
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @AfterEach
    public void tearDown() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void GetAcceptedFriendsByAuth0Id_FindFriends_ReturnAcceptedFriends() {
        
        // Arrange
        User friend1 = new User();
        friend1.setAuth0Id("auth0|1234567890");
        friend1.setEmail("integration@test.nl");
        friend1.setUsername("integrationTest");
        User friend2 = new User();
        friend2.setAuth0Id("auth0|0987654321");
        friend2.setEmail("test@integration.de");
        friend2.setUsername("TestIntegration");
        User_Friend userFriend1 = new User_Friend(friend1, friend2, true);
        userRepository.save(friend1);
        userRepository.save(friend2);
        friendRepository.save(userFriend1);

        // Act
        List<User> actualFriends = friendService.GetAcceptedFriendsByAuth0Id(friend1.getAuth0Id());
    
        // Assert
        assertEquals(1, actualFriends.size());
        assertEquals(actualFriends.get(0).getAuth0Id(), friend2.getAuth0Id());
        assertEquals(actualFriends.get(0).getEmail(), friend2.getEmail());
        assertEquals(actualFriends.get(0).getUsername(), friend2.getUsername());
    }
}