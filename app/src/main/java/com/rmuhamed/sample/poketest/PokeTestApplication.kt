package com.rmuhamed.sample.poketest

import com.rmuhamed.sample.poketest.config.AppConfiguration
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.data.RepositoryFactory
import com.rmuhamed.sample.poketest.model.Pokemon

class PokeTestApplication : TemplatePokeTestApplication() {

    override val networkRepository: IRepository<Pokemon> by lazy {
        RepositoryFactory.Builder()
            .configuration(configuration)
            .build()
            .create(RepositoryFactory.Type.NETWORK)
    }

    override val persistenceRepository: IRepository<Pokemon> by lazy {
        RepositoryFactory.Builder()
            .configuration(configuration)
            .build()
            .create(RepositoryFactory.Type.DATABASE)
    }

    override fun getAppConfiguration(): AppConfiguration =
        AppConfiguration(this, BuildConfig.DB_NAME, BuildConfig.BASE_API_URL);

}