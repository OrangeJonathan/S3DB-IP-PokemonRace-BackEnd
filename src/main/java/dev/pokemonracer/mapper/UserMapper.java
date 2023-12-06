package dev.pokemonracer.mapper;

import dev.pokemonracer.DTOs.UserDTO;
import dev.pokemonracer.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getAuth0Id());
        return userDTO;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setAuth0Id(userDTO.getAuth0_id());
        return user;
    }
}
    
