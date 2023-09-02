package com.parkerrandolph.pokemoncards.models;

import java.util.List;

public class PokemonInfo {

    private String name;

    private List<Type> types;
    private long id;
    private long weight;
    private long height;
    private long base_experience;

    

    public String getName() {
        return name;
    }

    public List<Type> getTypes(){
        return types;
    }


    public long getHeight() {
        return height;
    }

    public long getWeight() {
        return weight;
    }

    public long getBase_experience() {
        return base_experience;
    }

    public long getId() {
        return id;
    }


}
