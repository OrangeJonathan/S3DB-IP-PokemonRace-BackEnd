package dev.pokemonracer.model;

public class Pokemon {

    private int id;
    private String name;
    private String type;
    private String imageString;

    public Pokemon(int id, String name, String type, String imageString) {
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
