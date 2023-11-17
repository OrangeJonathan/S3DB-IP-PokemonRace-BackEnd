package dev.pokemonracer;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import dev.pokemonracer.service.PokeAPIservice;

@SpringBootTest
class PokemonracerApplicationTests {

	@Test
    void resetPokemon_removePokemonIDFromSet_shouldBeRemovedFromSet() {
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
        Assertions.assertThat(pokeAPIservice.getGeneratedPokemonIds()).isEmpty();
    }
    
	@ParameterizedTest
    @CsvSource({
            "0, 1, 1010",
            "1, 1, 151",
            "2, 151, 251",
            "3, 252, 386",
            "4, 387, 493",
            "5, 494, 649",
            "6, 650, 721",
            "7, 722, 809",
            "8, 809, 898",
            "9, 899, 1010"
    })
    void getRandomPokemonID_generateRandomPokemonId_shouldBeInSpecifiedRange(int generation, int lowerBound, int upperBound) {
        // arrange
        var pokeAPIservice = new PokeAPIservice(null);

        // act
        pokeAPIservice.setPokemonGeneration(generation);
        int id = pokeAPIservice.generateRandomPokemonId();

        // assert
        Assertions.assertThat(id).isBetween(lowerBound, upperBound);
        Assertions.assertThat(pokeAPIservice.getPokemonGeneration()).isEqualTo(generation);
        Assertions.assertThat(id).isGreaterThan(0);
    }

}
