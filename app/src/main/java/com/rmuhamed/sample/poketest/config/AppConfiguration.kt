package com.rmuhamed.sample.poketest.config

import android.content.Context
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.data.RepositoryFactory
import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppConfiguration private constructor(context: Context, dbName: String, baseUrl: String) {

    val appDatabase: AppDatabase
    val apiDefinition: RestApiDefinition
    val threadExecutor: ExecutorService

    val networkRepository: IRepository<Pokemon> by lazy {
        RepositoryFactory.Builder()
            .configuration(this)
            .build()
            .create(RepositoryFactory.Type.NETWORK)
    }

    val persistenceRepository: IRepository<Pokemon> by lazy {
        RepositoryFactory.Builder()
            .configuration(this)
            .build()
            .create(RepositoryFactory.Type.DATABASE)
    }

    init {
        this.appDatabase = DatabaseConfigurator.createDatabase(dbName, context)
        this.apiDefinition = RestApiConfigurator.createApi(baseUrl)
        this.threadExecutor = Executors.newFixedThreadPool(4)

        CONFIGURED = true
    }

    companion object {
        private var CONFIGURED: Boolean = false
        private lateinit var INSTANCE: AppConfiguration

        fun get(): AppConfiguration {
            return if (!CONFIGURED) {
                throw RuntimeException("Not configured App")
            } else {
                INSTANCE
            }
        }

        fun configure(context: Context, dbName: String, baseUrl: String) {
            INSTANCE = AppConfiguration(context, dbName, baseUrl)

        }
    }
}
