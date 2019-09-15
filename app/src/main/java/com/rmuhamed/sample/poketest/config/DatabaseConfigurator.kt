package com.rmuhamed.sample.poketest.config

import android.content.Context
import androidx.room.Room

object DatabaseConfigurator {
    @JvmStatic
    fun createDatabase(dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
    }
}
