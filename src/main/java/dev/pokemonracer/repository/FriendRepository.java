package dev.pokemonracer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.pokemonracer.model.User;
import dev.pokemonracer.model.User_Friend;

@Repository
public interface FriendRepository extends JpaRepository<User_Friend, Long> {

    List<User_Friend> findByIdUserAndIsAccepted(User user, boolean isAccepted);

    List<User_Friend> findByIdFriendAndIsAccepted(User friend, boolean isAccepted);

    User_Friend findByIdUserAndIdFriend(User user, User friend);
}
