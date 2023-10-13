package dev.pokemonracer;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.pokemonracer.service.PokeAPIservice;

@SpringBootTest
class PokemonracerApplicationTests {

	@Test
	void resetPokemon_removePokemonIDFromSet_shouldBeRemovedFromSet()
	{
		// arrange
		PokeAPIservice pokeAPIservice = new PokeAPIservice(null);

		Set<Integer> generatedPokemonIds = new HashSet<>();
		generatedPokemonIds.add(1);
		generatedPokemonIds.add(2);
		generatedPokemonIds.add(3);

		pokeAPIservice.setGeneratedPokemonIds(generatedPokemonIds);
		// act
		pokeAPIservice.resetGuessedPokemonList();
		// assert
		assert(pokeAPIservice.getGeneratedPokemonIds().isEmpty());
	}

}
