// package dev.pokemonracer.IntegrationTests;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import dev.pokemonracer.exceptions.PokemonNotFoundException;
// import dev.pokemonracer.model.Pokemon;
// import dev.pokemonracer.repository.PokemonAPIRepository;
// import dev.pokemonracer.service.PokeAPIservice;


// @SpringBootTest
// public class PokeAPIserviceIT {
//     private PokeAPIservice pokeAPIservice;

//     @Autowired
//     public PokeAPIserviceIT(PokeAPIservice pokeAPIservice, PokemonAPIRepository pokemonAPIRepository) {
//         this.pokeAPIservice = pokeAPIservice;
//     }

//     @Test
//     public void GetPokemonWithId_FindPokemon_ReturnsPokemon() {
        
//         // Arrange
//         int id = 1;
        
//         // Act
//         Pokemon actualPokemon = pokeAPIservice.getPokemonWithId(id);

//         // Assert
//         assertEquals(1, actualPokemon.getId());
//         assertEquals("bulbasaur", actualPokemon.getName());
//         assertEquals("grass", actualPokemon.getType());
//         assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", actualPokemon.getImageString());
//     }

//     @Test   
//     public void GetPokemonWithId_PokemonDoesNotExist_ReturnsNull() {

//         // Arrange
//         int id = 99999;

//         // Act and Assert
//         assertThrows(PokemonNotFoundException.class, () -> {
//         pokeAPIservice.getPokemonWithId(id);
//     });;
//     }
// }
