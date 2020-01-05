package com.rmuhamed.sample.poketest.ui

import android.app.Application
import com.rmuhamed.sample.poketest.BuildConfig
import com.rmuhamed.sample.poketest.config.AppConfiguration
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.data.RepositoryFactory
import com.rmuhamed.sample.poketest.model.Pokemon

class PokeTestApplication : Application() {
    private lateinit var configuration: AppConfiguration

    val networkRepository: IRepository<Pokemon> by lazy {
        RepositoryFactory.Builder()
            .configuration(configuration)
            .build()
            .create(RepositoryFactory.Type.NETWORK)
    }

    val persistenceRepository: IRepository<Pokemon> by lazy {
        RepositoryFactory.Builder()
            .configuration(configuration)
            .build()
            .create(RepositoryFactory.Type.DATABASE)
    }

    override fun onCreate() {
        super.onCreate()

        configuration = AppConfiguration(this, BuildConfig.DB_NAME, BuildConfig.BASE_API_URL);
    }

}