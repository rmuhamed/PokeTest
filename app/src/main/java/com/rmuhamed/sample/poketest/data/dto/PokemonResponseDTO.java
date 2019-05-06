package com.rmuhamed.sample.poketest.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonResponseDTO {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("base_experience")
    private String baseExperience;
    @SerializedName("height")
    private String height;
    @SerializedName("weight")
    private String weight;
    @SerializedName("types")
    private List<Types> types;
    @SerializedName("sprites")
    private Sprites sprites;

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(String baseExperience) {
        this.baseExperience = baseExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public class Types {
        @SerializedName("type")
        private Type type;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }

    public class Type {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Sprites {
        @SerializedName("front_default")
        private String front;

        public String getFront() {
            return front;
        }

        public void setFront(String front) {
            this.front = front;
        }
    }
}
