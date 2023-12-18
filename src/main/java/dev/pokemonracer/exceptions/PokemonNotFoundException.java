package dev.pokemonracer.exceptions;

public class PokemonNotFoundException extends RuntimeException{

    public PokemonNotFoundException(String message) {
        super(message);
    }

    public PokemonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
