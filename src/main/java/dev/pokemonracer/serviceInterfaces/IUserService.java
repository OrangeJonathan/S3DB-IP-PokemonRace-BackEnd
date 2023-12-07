package dev.pokemonracer.serviceInterfaces;

import dev.pokemonracer.model.User;

public interface IUserService {
    void createUser(User user);
    public User getUserByAuth0Id(String auth0Id);
    public User getUserById(Long id);
    public User getUserByEmail(String receiver_email);
}