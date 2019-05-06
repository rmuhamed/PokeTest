package com.rmuhamed.sample.poketest.model;

import java.util.Date;

public class Pokemon {
    private String id;
    private String type;
    private String name;
    private String picture;
    private String weight;
    private String height;
    private String baseExperience;

    private Date capturedAt;

    private Pokemon(Pokemon.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.picture = builder.picture;
        this.weight = builder.weight;
        this.height = builder.height;
        this.baseExperience = builder.baseExperience;
        this.capturedAt = builder.capturedAt;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPicture() {
        return picture;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getBaseExperience() {
        return baseExperience;
    }

    public Date getCapturedAt() {
        return capturedAt;
    }

    public static class Builder {
        private String name;
        private String id;
        private String type;
        private String picture;
        private String weight;
        private String height;
        private String baseExperience;
        private Date capturedAt;

        public Builder setName(String name) {
            this.name = name;

            return this;
        }

        public Builder setId(String id) {
            this.id = id;

            return this;
        }

        public Builder setType(String type) {
            this.type = type;

            return this;
        }

        public Builder setPicture(String picture) {
            this.picture = picture;

            return this;
        }

        public Builder setWeight(String weight) {
            this.weight = weight;

            return this;
        }

        public Builder setHeight(String height) {
            this.height = height;

            return this;
        }

        public Builder setBaseExperience(String baseExperience) {
            this.baseExperience = baseExperience;

            return this;
        }

        public Builder capturedAt(Date capturedAt) {
            this.capturedAt = capturedAt;

            return this;
        }

        public Pokemon build() {
            return new Pokemon(this);
        }
    }
}
