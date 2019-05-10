package com.rmuhamed.sample.poketest.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDAO {
    @Query("SELECT * FROM PokemonEntity")
    List<PokemonEntity> findAll();

    @Query("SELECT COUNT(*) FROM PokemonEntity WHERE id = :id")
    int countBy(String id);

    @Insert
    void save(PokemonEntity aPokemonEntity);
}
