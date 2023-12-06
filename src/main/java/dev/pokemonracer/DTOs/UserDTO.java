package dev.pokemonracer.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    private String username;
    private String auth0_id;
    
    public UserDTO(String username, String id) {
        this.username = username;
        this.auth0_id = id;
    }
}
