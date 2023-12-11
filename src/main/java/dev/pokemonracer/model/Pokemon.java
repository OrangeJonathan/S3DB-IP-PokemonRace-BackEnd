package dev.pokemonracer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {

    private int id;
    private String name;
    private String type;
    private String imageString;
    
    public Pokemon(int id, String name, String imageString) {
        this.id = id;
        this.name = name;
        this.imageString = imageString;
    }
    
}
