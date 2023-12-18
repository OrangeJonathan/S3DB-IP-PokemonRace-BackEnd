package dev.pokemonracer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.User_Friend;
import dev.pokemonracer.repository.FriendRepository;
import dev.pokemonracer.serviceInterfaces.IFriendService;
import dev.pokemonracer.serviceInterfaces.IUserService;

@Service
public class FriendService implements IFriendService {
    private FriendRepository friendRepository;
    private IUserService userService;

    public FriendService(FriendRepository friendRepository, IUserService userService) {
        this.friendRepository = friendRepository;
        this.userService = userService;
    }

    public List<User> GetAcceptedFriendsByAuth0Id(String auth0Id) {
        Boolean accepted = true;
        return GetFriendsByAuth0Id(auth0Id, accepted);
    }
    
    public List<User> GetPendingFriendsByAuth0Id(String auth0Id) {
        Boolean accepted = false;
        return GetFriendsByAuth0Id(auth0Id, accepted);
    }

    public void SendFriendRequest(String sender_auth0Id, String receiver_email) {
        User sendUser = userService.getUserByAuth0Id(sender_auth0Id);
        User receiveUser = userService.getUserByEmail(receiver_email);

        if(getUser_friend(sender_auth0Id, receiveUser.getAuth0Id()) != null) return;

        User_Friend userFriend = new User_Friend(sendUser, receiveUser, false);
        friendRepository.save(userFriend);
    }

    public void AcceptFriendRequest(String sender_auth0Id, String receiver_auth0Id) {
        User_Friend userFriend = getUser_friend(sender_auth0Id, receiver_auth0Id);
        if(getUser_friend(sender_auth0Id, receiver_auth0Id) == null) return;

        userFriend.setAccepted(true);
        friendRepository.save(userFriend);
    }

    public void DeleteFriend(String sender_auth0Id, String receiver_auth0Id) {
        User_Friend userFriend = getUser_friend(sender_auth0Id, receiver_auth0Id);
        if(getUser_friend(sender_auth0Id, receiver_auth0Id) == null) return;

        friendRepository.delete(userFriend);
    }

    private User_Friend getUser_friend(String sender_auth0Id, String receiver_auth0Id) {
        User sender = userService.getUserByAuth0Id(sender_auth0Id);
        User receiver = userService.getUserByAuth0Id(receiver_auth0Id);
        User_Friend userFriend = friendRepository.findByIdUserAndIdFriend(sender, receiver);
        if (userFriend == null) userFriend = friendRepository.findByIdUserAndIdFriend(receiver, sender);
        return userFriend;
    }

    private List<User> GetFriendsByAuth0Id(String auth0Id, Boolean accepted) {
        User user = userService.getUserByAuth0Id(auth0Id);
        Long userId = user.getId();
        
        List<User_Friend> userFriends = friendRepository.findByIdFriendAndIsAccepted(user, accepted);
        if (accepted) userFriends.addAll(friendRepository.findByIdUserAndIsAccepted(user, accepted));
        List<User> friendList = new ArrayList<>();
    
        for (User_Friend userFriend : userFriends) {
            User friendUser = userFriend.getId().getFriend();
            if (friendUser.getId().equals(userId)) friendUser = userFriend.getId().getUser();
            User friend = (friendUser.getId() != userId) ? friendUser : user;
            friendList.add(friend);
        }
    
        return friendList;
    }    

    
}
