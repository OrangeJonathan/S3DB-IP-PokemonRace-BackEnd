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
        int generation = 0;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 1 && id <= 1010);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween1And151()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 1;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 1 && id <= 151);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween152And251()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 2;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 151 && id <= 251);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween252And386()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 3;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 252 && id <= 386);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}
    
    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween387And493()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 4;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 387 && id <= 493);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween494And649()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 5;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 494 && id <= 649);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween650And721()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 6;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 650 && id <= 721);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween722And809()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 7;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 722 && id <= 809);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween810And898()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 8;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 809 && id <= 898);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

    @Test
	void getRandomPokemonID_generateRandomPokemonId_shouldBeBetween899And1010()
	{
		// arrange
		var pokeAPIservice = new PokeAPIservice(null);
		int id;
        int generation = 9;

		// act
        pokeAPIservice.setPokemonGeneration(generation);
		id = pokeAPIservice.generateRandomPokemonId();

		// assert
		assert(id >= 899 && id <= 1010);
        assert(pokeAPIservice.getPokemonGeneration() == generation);
        assert(id > 0);
	}

}
