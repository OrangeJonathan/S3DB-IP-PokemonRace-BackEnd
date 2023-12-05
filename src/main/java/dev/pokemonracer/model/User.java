package dev.pokemonracer.model;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false)
    private String username;

    @Column(name="auth0_Id", nullable=false)
    private String auth0_Id;

    private boolean isOnline;

    User() {}

    public User(String username, String auth0_Id) {
        this.username = username;
        this.auth0_Id = auth0_Id;
    }
}
