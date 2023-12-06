package dev.pokemonracer.serviceInterfaces;

import dev.pokemonracer.DTOs.UserDTO;

public interface IUserService {
    void createUser(UserDTO userDTO);
}