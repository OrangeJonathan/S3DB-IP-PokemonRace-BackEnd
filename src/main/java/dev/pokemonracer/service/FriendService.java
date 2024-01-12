package dev.pokemonracer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.UserFriend;
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

    public List<User> getAcceptedFriendsByAuth0Id(String auth0Id) {
        Boolean accepted = true;
        return getFriendsByAuth0Id(auth0Id, accepted);
    }
    
    public List<User> getPendingFriendsByAuth0Id(String auth0Id) {
        Boolean accepted = false;
        return getFriendsByAuth0Id(auth0Id, accepted);
    }

    public void sendFriendRequest(String senderAuth0Id, String receiverEmail) {
        User sendUser = userService.getUserByAuth0Id(senderAuth0Id);
        User receiveUser = userService.getUserByEmail(receiverEmail);
        
        if(getUserFriend(senderAuth0Id, receiveUser.getAuth0Id()) != null) return;

        UserFriend userFriend = new UserFriend(sendUser, receiveUser, false);
        friendRepository.save(userFriend);
    }

    public UserFriend acceptFriendRequest(String senderAuth0Id, String receiverEmail) {
        UserFriend userFriend = getUserFriend(senderAuth0Id, receiverEmail);
        if(getUserFriend(senderAuth0Id, receiverEmail) == null) return null;

        userFriend.setAccepted(true);
        friendRepository.save(userFriend);
        return userFriend;
    }

    public void deleteFriend(String senderAuth0Id, String receiverEmail) {
        UserFriend userFriend = getUserFriend(senderAuth0Id, receiverEmail);
        if(getUserFriend(senderAuth0Id, receiverEmail) == null) return;

        friendRepository.delete(userFriend);
    }

    public UserFriend getUserFriend(String senderAuth0Id, String receiverEmail) {
        User sender = userService.getUserByAuth0Id(senderAuth0Id);
        User receiver = userService.getUserByAuth0Id(receiverEmail);
        UserFriend userFriend = friendRepository.findByIdUserAndIdFriend(sender, receiver);
        if (userFriend == null) userFriend = friendRepository.findByIdUserAndIdFriend(receiver, sender);
        return userFriend;
    }

    public List<User> getFriendsByAuth0Id(String auth0Id, Boolean accepted) {
        User user = userService.getUserByAuth0Id(auth0Id);
        Long userId = user.getId();
        
        List<UserFriend> userFriends = friendRepository.findByIdFriendAndIsAccepted(user, accepted);
        if (Boolean.TRUE.equals(accepted)) userFriends.addAll(friendRepository.findByIdUserAndIsAccepted(user, accepted));
        List<User> friendList = new ArrayList<>();
    
        for (UserFriend userFriend : userFriends) {
            User friendUser = userFriend.getId().getFriend();
            if (friendUser.getId().equals(userId)) friendUser = userFriend.getId().getUser();
            User friend = (friendUser.getId() != userId) ? friendUser : user;
            friendList.add(friend);
        }
    
        return friendList;
    }    
}
