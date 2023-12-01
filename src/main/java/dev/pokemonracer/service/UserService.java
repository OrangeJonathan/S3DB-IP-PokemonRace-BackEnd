package dev.pokemonracer.service;

import org.springframework.stereotype.Service;

import dev.pokemonracer.DTOs.UserDTO;
import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO UserDTO) {
        User user = convertToUserEntity(UserDTO);
        userRepository.save(user);
    }

    private User convertToUserEntity(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getId());
        return user; 
    }
}
