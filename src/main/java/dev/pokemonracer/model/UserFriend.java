package dev.pokemonracer.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_friend")
public class UserFriend {
    
    @EmbeddedId
    private FriendID id;

    @Column(name="IsAccepted", nullable = false)
    private boolean isAccepted;

    public UserFriend(User userId, User friendId, boolean isAccepted) {
        this.id = new FriendID(userId, friendId);
        this.isAccepted = isAccepted;
    }
}