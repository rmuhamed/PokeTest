package com.rmuhamed.sample.poketest.config

import android.content.Context
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppConfiguration(context: Context, dbName: String, baseUrl: String) {
    val appDatabase: AppDatabase = DatabaseConfigurator.createDatabase(dbName, context)
    val apiDefinition: RestApiDefinition = RestApiConfigurator.createApi(baseUrl)
    val threadExecutor: ExecutorService = Executors.newFixedThreadPool(4)
}
