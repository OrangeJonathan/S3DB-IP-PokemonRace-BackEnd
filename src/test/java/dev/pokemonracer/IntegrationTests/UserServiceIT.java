package dev.pokemonracer.IntegrationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.FriendRepository;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;

@Testcontainers
@SpringBootTest
public class UserServiceIT {

    @Container
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7")
            .withDatabaseName("pokemonracerTest")
            .withUsername("root")
            .withPassword("RooTPassworD1!")
            .waitingFor(Wait.forHealthcheck())
            .withStartupTimeout(Duration.ofSeconds(120));

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendRepository friendRepository;

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @BeforeEach
    public void tearDown() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterEach
    public void printContainerLogs() {
        String logs = mySQLContainer.getLogs();
        System.out.println("MySQL Logs:");
        System.out.println(logs);
    }

    @Test
    void GetUserByAuth0Id_FindUser_ReturnsUser() {
        
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
    void GetUserByEmail_FindUser_ReturnsUser() {
        
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
    void GetUserById_FindUser_ReturnsUser() {
        
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
    void CreateUser_UserDoesNotExist_UserCreated() {
        
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
