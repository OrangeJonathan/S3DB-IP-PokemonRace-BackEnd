package dev.pokemonracer.model;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false)
    private String username;

    @Column(name="auth0_Id", nullable=false)
    private String auth0Id;

    @Column(name="email", nullable=false)
    private String email;

    private boolean isOnline;

    public User(String username, String auth0_Id) {
        this.username = username;
        this.auth0Id = auth0_Id;
    }
}
