package com.rmuhamed.sample.poketest.config

import android.content.Context
import androidx.room.Room

object DatabaseConfigurator {
    @JvmStatic
    fun getInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "pokemon-db").build()
    }
}
