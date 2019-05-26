package com.rmuhamed.sample.poketest.config

import android.content.Context
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppConfigurator private constructor(context: Context, baseUrl: String) {

    val appDatabase: AppDatabase
    val apiDefinition: RestApiDefinition
    val threadExecutor: ExecutorService

    init {
        if (INITIALIZED) {
            throw RuntimeException("Already initialized App Configuration")
        } else {
            INITIALIZED = true

            this.appDatabase = DatabaseConfigurator.createDatabase(context)
            this.apiDefinition = RestApiConfigurator.createApi(baseUrl)
            this.threadExecutor = Executors.newFixedThreadPool(4)
        }
    }

    companion object {
        private var INITIALIZED: Boolean = false

        fun initialize(context: Context, baseUrl: String): AppConfigurator {
            return AppConfigurator(context, baseUrl)
        }
    }
}
