package com.rmuhamed.sample.poketest.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmuhamed.sample.poketest.data.dao.PokemonDAO
import com.rmuhamed.sample.poketest.data.dao.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO
}
