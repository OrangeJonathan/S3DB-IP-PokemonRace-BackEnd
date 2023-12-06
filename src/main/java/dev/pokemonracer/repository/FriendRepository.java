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

    List<User_Friend> findByIdUser(User user);

    List<User_Friend> findByIdFriend(User friend);

    User_Friend findByIdUserAndIdFriend(User user, User friend);
}
