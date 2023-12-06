package dev.pokemonracer.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class User_Friend {
    
    @EmbeddedId
    private FriendID id;

    @Column(name="IsAccepted", nullable = false)
    private boolean isAccepted;

    public User_Friend(User user_Id, User friend_Id, boolean isAccepted) {
        this.id = new FriendID(user_Id, friend_Id);
        this.isAccepted = isAccepted;
    }
}
