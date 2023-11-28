package dev.pokemonracer.DTOs;
import dev.pokemonracer.model.Pokemon;
import jakarta.validation.constraints.NotEmpty;

public class PokemonDTO {

    @NotEmpty
    private int id;
    
    @NotEmpty
    private String name;

    @NotEmpty
    private String imageString;

    public PokemonDTO(int id, String name, String imageString) {
        this.id = id;
        this.name = name;
        this.imageString = imageString;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
