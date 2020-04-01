package com.rmuhamed.sample.poketest.config

import android.content.Context

class AppConfiguration(context: Context, dbName: String, baseUrl: String) {
    val appDatabase: AppDatabase = DatabaseConfigurator.createDatabase(dbName, context)
    val apiDefinition: PokeAPIDefinition = RestApiConfigurator.createApi(baseUrl)
}
