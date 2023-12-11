package dev.pokemonracer.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="User_Generation_Highscore")
public class User_Generation_Highscore {
    
    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User user_Id;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private Generation generation_Id;

    @Column(name="Highscore")
    private int highscore;

    User_Generation_Highscore() {}

    User_Generation_Highscore(User user_Id, Generation generation_Id, int highscore) {
        this.user_Id = user_Id;
        this.generation_Id = generation_Id;
        this.highscore = highscore;
    }
}
