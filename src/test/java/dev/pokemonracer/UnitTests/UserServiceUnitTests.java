package dev.pokemonracer.UnitTests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void test() {
        Assertions.assertThat(true);
    }

}
