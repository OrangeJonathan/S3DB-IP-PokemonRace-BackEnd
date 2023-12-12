package dev.pokemonracer.DTOs;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDTO {

    @NotEmpty
    private int id;
    
    @NotEmpty
    private String name;

    @NotEmpty
    private String imageString;
}
