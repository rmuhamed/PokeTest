package com.rmuhamed.sample.poketest.data.dao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity
public class PokemonEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "pictureUrl")
    private String picture;
    @ColumnInfo(name = "weight")
    private String weight;
    @ColumnInfo(name = "height")
    private String height;
    @ColumnInfo(name = "baseExperience")
    private String baseExperience;
    @ColumnInfo(name = "capturedAtMillis")
    private long capturedAt;

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public long getCapturedAt() {
        return capturedAt;
    }

    public void setCapturedAt(long capturedAt) {
        this.capturedAt = capturedAt;
    }
}
