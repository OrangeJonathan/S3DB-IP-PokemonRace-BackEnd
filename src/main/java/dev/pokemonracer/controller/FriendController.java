package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.DTOs.UserDTO;
import dev.pokemonracer.mapper.UserMapper;
import dev.pokemonracer.model.User;
import dev.pokemonracer.serviceInterfaces.IFriendService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/friends")
public class FriendController {
    
    private IFriendService friendService;
    private UserMapper mapper;

    public FriendController(IFriendService friendService, UserMapper mapper) {
        this.friendService = friendService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<UserDTO> GetAcceptedFriends(@RequestParam String auth0Id) {
        List<User> friendList = friendService.GetAcceptedFriendsByAuth0Id(auth0Id);
        List<UserDTO> friendDTOList = new ArrayList<>();
        for (User user : friendList) {
            friendDTOList.add(mapper.toUserDTO(user));
            System.out.println(user.getAuth0Id());

        }
        return friendDTOList;
    }

    @GetMapping("/pending")
    public List<UserDTO> GetPendingFriends(@RequestParam String auth0Id) {
        List<User> friendList = friendService.GetPendingFriendsByAuth0Id(auth0Id);
        List<UserDTO> friendDTOList = new ArrayList<>();
        for (User user : friendList) {
            friendDTOList.add(mapper.toUserDTO(user));
        }
        return friendDTOList;
    }
    
    
    @PostMapping("")
    public void SendFriendRequest(@RequestParam("senderAuth0Id") String senderAuth0Id, @RequestParam("receiverEmail") String receiverEmail) {
        friendService.SendFriendRequest(senderAuth0Id, receiverEmail);
    }

    @PutMapping("")
    public void AcceptFriendRequest(@RequestParam String senderAuth0Id, @RequestParam String receiverAuth0Id) {
        friendService.AcceptFriendRequest(senderAuth0Id, receiverAuth0Id);
    }

}
