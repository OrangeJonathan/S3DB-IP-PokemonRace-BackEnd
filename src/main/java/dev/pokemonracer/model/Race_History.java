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

@Entity
@Table(name="Race_History")
public class Race_History {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User player1_Id;

    @Column(name="Player1_Score")
    private int player1_Score;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User player2_Id;

    @Column(name="Player2_Score")
    private int player2_Score;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private Generation generation_Id;

    @Temporal(TemporalType.TIME)
    @Column(name="TimeLimit")
    private java.util.Date timeLimit;
}
