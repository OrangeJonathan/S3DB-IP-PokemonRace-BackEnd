package dev.pokemonracer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name="Race")
public class Race {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="Player1")
    private User player1;

    @Column(name="Player1_Score")
    private int player1_Score;

    @ManyToOne
    @JoinColumn(name="Player2")
    private User player2;

    @Column(name="Player2_Score")
    private int player2_Score;

    @Column(name="Status")
    private RaceStatus status;

    @ManyToOne
    @JoinColumn(name="Generation")
    private Generation generation;

    @Temporal(TemporalType.TIME)
    @Column(name="TimeLimit")
    private java.util.Date timeLimit;
}
