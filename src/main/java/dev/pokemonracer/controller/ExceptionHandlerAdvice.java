package dev.pokemonracer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.pokemonracer.exceptions.PokemonApiException;
import dev.pokemonracer.exceptions.PokemonNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    
    @ExceptionHandler(PokemonApiException.class)
    public ResponseEntity handleException(PokemonApiException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity handleException(PokemonNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
