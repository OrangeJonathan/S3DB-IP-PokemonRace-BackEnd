package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.UserFriend;

public interface IFriendService {
    public List<User> getAcceptedFriendsByAuth0Id(String auth0Id);
    public List<User> getPendingFriendsByAuth0Id(String auth0Id);
    public void sendFriendRequest(String senderAuth0Id, String receiverEmail);
    public UserFriend acceptFriendRequest(String senderAuth0Id, String receiverAuth0Id);
    public void deleteFriend(String senderAuth0Id, String receiverAuth0Id);
}
