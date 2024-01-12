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
    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private Generation generationId;

    @Column(name="Highscore")
    private int highscore;

    User_Generation_Highscore() {}

    User_Generation_Highscore(User userId, Generation generationId, int highscore) {
        this.userId = userId;
        this.generationId = generationId;
        this.highscore = highscore;
    }
}
