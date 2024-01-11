package dev.pokemonracer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dev.pokemonracer")
public class PokemonracerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonracerApplication.class, args);
	}
}
