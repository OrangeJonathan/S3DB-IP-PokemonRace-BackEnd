package dev.pokemonracer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Generation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Generation {
    
    @Id
    @Column(name = "GenerationId")
    private Long id;

    @Column(name="LowerLimit", nullable = false)
    private int lowerLimit;

    @Column(name="UpperLimit", nullable = false)
    private int upperLimit;
}
