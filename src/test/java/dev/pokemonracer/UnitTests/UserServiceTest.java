package dev.pokemonracer.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void CreateUser_UserDoesNotExist_UserShouldBeCreated() {
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
    public void GetUserByAuth0Id_UserExists_UserShouldBeReturned() {
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
    public void GetUserById_UserExists_UserShouldBeReturned() {
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
    public void GetUserByEmail_UserExists_UserShouldBeReturned() {
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
    public void GetUserByAuth0Id_UserDoesNotExist_ShouldReturnNull() {
        // Arrange
        when(userRepository.findByAuth0Id("nonexistent")).thenReturn(null);

        // Act
        User result = userService.getUserByAuth0Id("nonexistent");

        // Assert
        assertNull(result);
    }

}
