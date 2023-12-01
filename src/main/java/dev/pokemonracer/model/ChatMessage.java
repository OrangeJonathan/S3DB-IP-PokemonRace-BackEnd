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
@Table(name="ChatMessage")
public class ChatMessage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User sender_Id;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User recepient_Id;

    @Column(name="Message", nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TimeSent", nullable = false)
    private java.util.Date timeSent;

    ChatMessage() {}

    ChatMessage(User sender_Id, User recepient_Id, String message, java.util.Date timeSent) {
        this.sender_Id = sender_Id;
        this.recepient_Id = recepient_Id;
        this.message = message;
        this.timeSent = timeSent;
    }

}
