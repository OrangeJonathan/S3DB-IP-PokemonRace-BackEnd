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
        User user = userRepository.findByAuth0Id(auth0Id);
        if (user == null) return null;
        return user;
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        return user;
    }

    public User getUserByEmail(String recieverEmail) {
        User user = userRepository.findUserByEmail(recieverEmail);
        if (user == null) return null;	
        return user;
    }

}
