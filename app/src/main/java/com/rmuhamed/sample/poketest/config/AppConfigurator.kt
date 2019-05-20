package com.rmuhamed.sample.poketest.config

import android.content.Context

class AppConfigurator private constructor(context: Context, baseUrl: String) {

    val appDatabase: AppDatabase
    val apiDefinition: RestApiDefinition

    init {
        if (INITIALIZED) {
            throw RuntimeException("Already initialized App Configuration")
        } else {
            INITIALIZED = true

            this.appDatabase = DatabaseConfigurator.createDatabase(context)
            this.apiDefinition = RestApiConfigurator.createApi(baseUrl)
        }
    }

    companion object {
        private var INITIALIZED: Boolean = false

        fun initialize(context: Context, baseUrl: String): AppConfigurator {
            return AppConfigurator(context, baseUrl)
        }
    }
}
