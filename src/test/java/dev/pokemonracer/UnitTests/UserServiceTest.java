package dev.pokemonracer.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.UserService;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        // Mock the UserRepository
        userRepository = mock(UserRepository.class);

        // Create the UserService with the mocked repository
        userService = new UserService(userRepository);
    }

    @Test
    public void CreateUser_UserDoesNotExist_UserShouldBeCreated() {
        // Create a sample user
        User user = new User();
        user.setAuth0Id("auth0id123");
        user.setEmail("test@example.com");

        // Mock the behavior of UserRepository.save()
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the method to be tested
        userService.createUser(user);

        // Verify that the save method was called with the correct user
        verify(userRepository).save(user);
    }

    @Test
    public void GetUserByAuth0Id_UserExists_UserShouldBeReturned() {
        // Create a sample user
        User user = new User();
        user.setAuth0Id("auth0id123");

        // Mock the behavior of UserRepository.findByAuth0Id()
        when(userRepository.findByAuth0Id("auth0id123")).thenReturn(user);

        // Call the method to be tested
        User result = userService.getUserByAuth0Id("auth0id123");

        // Verify that the correct user was returned
        assertEquals(user, result);
    }

    @Test
    public void GetUserById_UserExists_UserShouldBeReturned() {
        // Create a sample user
        User user = new User();
        user.setId(1L);

        // Mock the behavior of UserRepository.findById()
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Call the method to be tested
        User result = userService.getUserById(1L);

        // Verify that the correct user was returned
        assertEquals(user, result);
    }

    @Test
    public void GetUserByEmail_UserExists_UserShouldBeReturned() {
        // Create a sample user
        User user = new User();
        user.setEmail("test@example.com");

        // Mock the behavior of UserRepository.findUserByEmail()
        when(userRepository.findUserByEmail("test@example.com")).thenReturn(user);

        // Call the method to be tested
        User result = userService.getUserByEmail("test@example.com");

        // Verify that the correct user was returned
        assertEquals(user, result);
    }

    @Test
    public void GetUserByAuth0Id_UserDoesNotExist_ShouldReturnNull() {
        // Mock the behavior of UserRepository.findByAuth0Id() when user is not found
        when(userRepository.findByAuth0Id("nonexistent")).thenReturn(null);

        // Call the method to be tested
        User result = userService.getUserByAuth0Id("nonexistent");

        // Verify that null is returned for a non-existent user
        assertNull(result);
    }

}
