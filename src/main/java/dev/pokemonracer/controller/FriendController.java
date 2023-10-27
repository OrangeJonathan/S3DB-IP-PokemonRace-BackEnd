package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.model.Friend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    private List<Friend> friends;

    public FriendController() {
        friends = new ArrayList<>();
        friends.add(new Friend("SatoshiAsh", "Ash Ketchum"));
        friends.add(new Friend("Kasumi", "Misty"));
        friends.add(new Friend("TakeshiRock", "Brock Harrison"));
        friends.add(new Friend("ShigeruBlue", "Gary Oak"));
        friends.add(new Friend("PeekAtChu", "Pikachu"));
    }

    @GetMapping()
    public List<Friend> getAllFriends() {
        return friends;
    }

    @GetMapping("/{id}")
    public Friend get(@PathVariable String id) {
        return friends.stream().filter(f -> id.equals(f.getId())).findAny().orElse(null);
    }
    

}