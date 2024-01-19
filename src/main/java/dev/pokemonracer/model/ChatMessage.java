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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ChatMessage")
public class ChatMessage implements Comparable<ChatMessage>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    
    @ManyToOne
    @JoinColumn(name="sender_Id")
    private User senderId;

    
    @ManyToOne
    @JoinColumn(name="recepient_id")
    private User recepientId;

    @Column(name="Message", nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TimeSent", nullable = false)
    private java.util.Date timeSent;

    public ChatMessage(User senderId, User recepientId, String message, java.util.Date timeSent) {
        this.senderId = senderId;
        this.recepientId = recepientId;
        this.message = message;
        this.timeSent = timeSent;
    }

    @Override
    public int compareTo(ChatMessage cm) {
        return getTimeSent().compareTo(cm.getTimeSent());
    }

}
