package dev.pokemonracer.service;

import org.springframework.stereotype.Service;

import dev.pokemonracer.DTOs.UserDTO;
import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.serviceInterfaces.IUserService;

@Service
public class UserService implements IUserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO userDTO) {
        User user = convertToUserEntity(userDTO);
        if (getUserByAuth0Id(user.getAuth0Id()) != null) return;
        userRepository.save(user);
    }

    public User getUserByAuth0Id(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    private User convertToUserEntity(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getAuth0_id());
        return user; 
    }
}
