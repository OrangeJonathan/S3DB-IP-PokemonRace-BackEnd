package dev.pokemonracer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.pokemonracer.service.PokeAPIservice;

@SpringBootTest
class PokemonracerApplicationTests {

	/* @Test
	void resetPokemon_removePokemonIDFromSet_shouldBeRemovedFromSet()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
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

	@Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween1And1010()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;

		// act
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 1 && id <= 1010);
	} */

	/* @Test 
	void retrievePokemon_GetRandomPokemon_shouldNotBeNull()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id = 7;
		RestTemplate restTemplate = new RestTemplate();

		// act
		var pokemon = pokeAPIservice.getPokemonWithId(id);

		// assert
		assertEquals(7, pokemon.getId());
		assertEquals("Squirtle", pokemon.getName());
	} */

}
