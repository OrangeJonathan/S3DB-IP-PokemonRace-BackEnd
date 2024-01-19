package dev.pokemonracer.mapper;

import dev.pokemonracer.dto.UserDTO;
import dev.pokemonracer.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getAuth0Id(), user.getEmail());
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setAuth0Id(userDTO.getAuth0Id());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
    
