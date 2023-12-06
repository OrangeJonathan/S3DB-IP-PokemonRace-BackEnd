package dev.pokemonracer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.User_Friend;
import dev.pokemonracer.repository.FriendRepository;

@Service
public class FriendService {
    private FriendRepository friendRepository;
    private UserService userService;

    public FriendService(FriendRepository friendRepository, UserService userService) {
        this.friendRepository = friendRepository;
        this.userService = userService;
    }

    public List<User> getFriendsByAuth0Id(String auth0Id) {
        User user = userService.getUserByAuth0Id(auth0Id);
        Long userId = user.getId();
    
        List<User_Friend> userFriends = friendRepository.findByIdUser(user);
        userFriends.addAll(friendRepository.findByIdFriend(user));
        List<User> friendList = new ArrayList<>();
    
        for (User_Friend userFriend : userFriends) {
            if (!userFriend.isAccepted()) continue;
            User friendUser = userFriend.getId().getFriend();
            User friend = (friendUser.getId() != userId) ? friendUser : user;
    
            friendList.add(friend);
        }
    
        return friendList;
    }

    public void sendFriendRequest(String sender_auth0Id, String receiver_auth0Id) {
        User sendUser = userService.getUserByAuth0Id(sender_auth0Id);
        User receiveUser = userService.getUserByAuth0Id(receiver_auth0Id);

        User_Friend user_friend = new User_Friend(sendUser, receiveUser, false);
        friendRepository.save(user_friend);
    }

    public void acceptFriendRequest(String sender_auth0Id, String receiver_auth0Id) {
        User_Friend user_friend = friendRepository.findByIdUserAndIdFriend(userService.getUserByAuth0Id(sender_auth0Id), userService.getUserByAuth0Id(receiver_auth0Id));
        user_friend.setAccepted(true);
        friendRepository.save(user_friend);
    }
}
