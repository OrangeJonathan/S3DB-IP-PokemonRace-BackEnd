package dev.pokemonracer.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.UserService;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Mock the UserRepository
        userRepository = mock(UserRepository.class);

        // Create the UserService with the mocked repository
        userService = new UserService(userRepository);
    }

    @Test
    void CreateUser_UserDoesNotExist_UserShouldBeCreated() {
        // Arrange  
        User user = new User();
        user.setAuth0Id("auth0id123");
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.createUser(user);

        // Assert
        verify(userRepository).save(user);
    }

    @Test
    void CreateUser_UserAlreadyExists_UserShouldNotBeCreated() {
        // Arrange
        User user = new User();
        user.setAuth0Id("auth0|ID1234");
        user.setEmail("test@gmail.nl");
        when(userRepository.findByAuth0Id("auth0|ID1234")).thenReturn(user);
        
        // Act
        userService.createUser(user);

        // Assert
        verify(userRepository, never()).save(user);
    }

    @Test
    void GetUserByAuth0Id_UserExists_UserShouldBeReturned() {
        // Arrange
        User user = new User();
        user.setAuth0Id("auth0id123");

        when(userRepository.findByAuth0Id("auth0id123")).thenReturn(user);

        // Act
        User result = userService.getUserByAuth0Id("auth0id123");

        // Assert
        assertEquals(user, result);
    }

    @Test
    void GetUserById_UserExists_UserShouldBeReturned() {
        // Arrange
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void GetUserByEmail_UserExists_UserShouldBeReturned() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findUserByEmail("test@example.com")).thenReturn(user);

        // Act
        User result = userService.getUserByEmail("test@example.com");

        // Assert
        assertEquals(user, result);
    }

    @Test
    void GetUserByAuth0Id_UserDoesNotExist_ShouldReturnNull() {
        // Arrange
        when(userRepository.findByAuth0Id("nonexistent")).thenReturn(null);

        // Act
        User result = userService.getUserByAuth0Id("nonexistent");

        // Assert
        assertNull(result);
    }

    @Test
    void GetUserById_UserDoesNotExist_ShouldReturnNull() {
        // Arrange
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(151L);

        // Assert
        assertNull(result);
    }

}
