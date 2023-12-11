package dev.pokemonracer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.DTOs.UserDTO;
import dev.pokemonracer.mapper.UserMapper;
import dev.pokemonracer.model.User;
import dev.pokemonracer.serviceInterfaces.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private IUserService userService;
    private UserMapper mapper;

    public UserController(IUserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
