package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.DTOs.UserDTO;
import dev.pokemonracer.mapper.UserMapper;
import dev.pokemonracer.model.User;
import dev.pokemonracer.service.FriendService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/friends")
public class FriendController {
    private FriendService friendService;
    private UserMapper mapper;

    public FriendController(FriendService friendService, UserMapper mapper) {
        this.friendService = friendService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<UserDTO> getMethodName(@RequestParam String auth0Id) {
        List<User> friendList = friendService.getFriendsByAuth0Id(auth0Id);
        List<UserDTO> friendDTOList = new ArrayList<>();
        for (User user : friendList) {
            friendDTOList.add(mapper.toUserDTO(user));
        }
        return friendDTOList;
    }
    
    @PostMapping("/request")
    public void sendFriendRequest(@RequestBody String[] auth0Ids) {
        friendService.sendFriendRequest(auth0Ids[0], auth0Ids[1]);
    }

    @PutMapping("/accept")
    public void acceptFriendRequest(@RequestBody String[] auth0Ids) {
        friendService.acceptFriendRequest(auth0Ids[0], auth0Ids[1]);
    }

}
