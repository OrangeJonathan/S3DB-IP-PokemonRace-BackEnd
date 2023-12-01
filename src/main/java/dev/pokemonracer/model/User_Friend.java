package dev.pokemonracer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_friend")
public class User_Friend {
    
    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User user_Id;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User friend_Id;

    @Column(name="IsAccepted", nullable = false)
    private boolean isAccepted;

    User_Friend() {}

    User_Friend(User user_Id, User friend_Id, boolean isAccepted) {
        this.user_Id = user_Id;
        this.friend_Id = friend_Id;
        this.isAccepted = isAccepted;
    }
}
