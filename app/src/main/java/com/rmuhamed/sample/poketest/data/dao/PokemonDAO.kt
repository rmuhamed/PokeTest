package com.rmuhamed.sample.poketest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM PokemonEntity")
    suspend fun findAll(): List<PokemonEntity>

    @Query("SELECT COUNT(*) FROM PokemonEntity WHERE id = :id")
    suspend fun countBy(id: Int): Int

    @Insert
    suspend fun save(aPokemonEntity: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun findBy(id: Int): PokemonEntity?
}