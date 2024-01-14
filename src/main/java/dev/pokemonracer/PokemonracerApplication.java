package dev.pokemonracer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
    @PropertySource("classpath:application.properties")
})
@SpringBootApplication
@ComponentScan("dev.pokemonracer")
public class PokemonracerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonracerApplication.class, args);
	}
}
