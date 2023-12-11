package dev.pokemonracer.service;

import org.springframework.stereotype.Service;

import dev.pokemonracer.model.User;
import dev.pokemonracer.repository.UserRepository;
import dev.pokemonracer.serviceInterfaces.IUserService;

@Service
public class UserService implements IUserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        if (getUserByAuth0Id(user.getAuth0Id()) != null) return;
        userRepository.save(user);
    }

    public User getUserByAuth0Id(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String receiver_email) {
        User user = userRepository.findUserByEmail(receiver_email);
        System.out.println(user.getEmail());

        return user;
    }

}
