package dev.pokemonracer.DTOs;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PokemonDTO {

    @NotEmpty
    private int id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String type;
    
    @NotEmpty
    private String imageString;

    public PokemonDTO(int id, String name, String type, String imageString) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageString = imageString;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    public String getImageString() {
        return imageString;
    }
}
