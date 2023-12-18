package dev.pokemonracer.IntegrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.FriendRepository;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.UserService;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceIT {
    private UserService userService;
    private UserRepository userRepository;
    private FriendRepository friendRepository;


    @Autowired
    public UserServiceIT(UserService userService, UserRepository userRepository, FriendRepository friendRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @AfterEach
    public void tearDown() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void GetUserByAuth0Id_FindUser_ReturnsUser() {
        
        // Arrange
        User expectedUser = new User();
        expectedUser.setAuth0Id("auth0|1234567890");
        expectedUser.setEmail("integration@test.nl");
        expectedUser.setUsername("integrationTest");
        userRepository.save(expectedUser);
        
        // Act
        User actualUser = userService.getUserByAuth0Id(expectedUser.getAuth0Id());
    
        // Assert
        assertEquals(expectedUser.getAuth0Id(), actualUser.getAuth0Id());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
    }

    @Test
    public void GetUserByEmail_FindUser_ReturnsUser() {
        
        // Arrange
        User expectedUser = new User();
        expectedUser.setAuth0Id("auth0|1234568907");
        expectedUser.setEmail("test@integrationtest.nl");
        expectedUser.setUsername("integrationTest");
        userRepository.save(expectedUser);
        
        // Act
        User actualUser = userService.getUserByEmail(expectedUser.getEmail());

        // Assert
        assertEquals(expectedUser.getAuth0Id(), actualUser.getAuth0Id());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
    }        

    @Test
    public void GetUserById_FindUser_ReturnsUser() {
        
        // Arrange
        User expectedUser = new User();
        expectedUser.setAuth0Id("auth0|1234567890");
        expectedUser.setEmail("integration@test.nl");
        expectedUser.setUsername("integrationTest");
        userRepository.save(expectedUser);
        
        // Act
        User actualUser = userService.getUserById(expectedUser.getId());

        // Assert
        assertEquals(expectedUser.getAuth0Id(), actualUser.getAuth0Id());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
    }    

    @Test
    public void CreateUser_UserDoesNotExist_UserCreated() {
        
        // Arrange
        User user = new User();
        user.setAuth0Id("auth0|987654321");
        user.setEmail("integration@test.nl");
        user.setUsername("integrationTest");

        // Act
        userService.createUser(user);

        // Assert
        User actualUser = userRepository.findByAuth0Id(user.getAuth0Id());
        assertEquals(user.getAuth0Id(), actualUser.getAuth0Id());
        assertEquals(user.getEmail(), actualUser.getEmail());
        assertEquals(user.getUsername(), actualUser.getUsername());
    }

    
}
