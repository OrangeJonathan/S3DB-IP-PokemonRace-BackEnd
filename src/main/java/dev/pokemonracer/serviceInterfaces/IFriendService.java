package dev.pokemonracer.serviceInterfaces;

import java.util.List;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.User_Friend;

public interface IFriendService {
    public List<User> GetAcceptedFriendsByAuth0Id(String auth0Id);
    public List<User> GetPendingFriendsByAuth0Id(String auth0Id);
    public void SendFriendRequest(String sender_auth0Id, String receiver_email);
    public User_Friend AcceptFriendRequest(String sender_auth0Id, String receiver_auth0Id);
    public void DeleteFriend(String sender_auth0Id, String receiver_auth0Id);
}
