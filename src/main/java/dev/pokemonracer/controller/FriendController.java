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
        friends.add(new Friend("SpungeBub", "Spongebob Squarepants"));
        friends.add(new Friend("Petriq", "Patrick Star"));
        friends.add(new Friend("GreedyKrab", "Eugene Krabs"));
        friends.add(new Friend("CheekySands", "Sandy Cheeks"));
        friends.add(new Friend("EvilChum39", "Plankton"));
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