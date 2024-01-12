package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.dto.UserDTO;
import dev.pokemonracer.mapper.UserMapper;
import dev.pokemonracer.model.User;
import dev.pokemonracer.serviceInterfaces.IFriendService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
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
    public List<UserDTO> getFriends(@RequestParam String auth0Id, @RequestParam Boolean accepted) {
        List<User> friendList;
        if (Boolean.TRUE.equals(accepted)) friendList = friendService.getAcceptedFriendsByAuth0Id(auth0Id);
        else friendList = friendService.getPendingFriendsByAuth0Id(auth0Id);

        List<UserDTO> friendDTOList = new ArrayList<>();
        for (User user : friendList) {
            friendDTOList.add(mapper.toUserDTO(user));
        }
        return friendDTOList;
    }
    
    @PostMapping("")
    public void sendFriendRequest(@RequestParam("senderAuth0Id") String senderAuth0Id, @RequestParam("receiverEmail") String receiverEmail) {
        friendService.SendFriendRequest(senderAuth0Id, receiverEmail);
    }

    @PutMapping("")
    public void acceptFriendRequest(@RequestParam String senderAuth0Id, @RequestParam String receiverAuth0Id) {
        friendService.acceptFriendRequest(senderAuth0Id, receiverAuth0Id);
    }

    @DeleteMapping("")
    public void removeFriend(@RequestParam String senderAuth0Id, @RequestParam String receiverAuth0Id) {
        friendService.deleteFriend(senderAuth0Id, receiverAuth0Id);
    }

}
