package dev.pokemonracer.DTOs;

public class UserDTO {
    
    private String username;
    private String auth0_id;
    
    public UserDTO(String username, String id) {
        this.username = username;
        this.auth0_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return auth0_id;
    }

    public void setId(String id) {
        this.auth0_id = id;
    }
}
