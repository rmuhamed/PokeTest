package com.rmuhamed.sample.poketest.ui

import android.app.Application
import com.rmuhamed.sample.poketest.BuildConfig
import com.rmuhamed.sample.poketest.config.AppConfigurator
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.data.RepositoryFactory
import com.rmuhamed.sample.poketest.model.Pokemon


class PokeTestApplication : Application() {

    lateinit var appConfigurator: AppConfigurator

    lateinit var networkRepository: IRepository<Pokemon>
    lateinit var persistenceRepository: IRepository<Pokemon>

    override fun onCreate() {
        super.onCreate()

        appConfigurator = AppConfigurator.initialize(this, BuildConfig.BASE_API_URL);

        val repositoryFactory = RepositoryFactory.Builder()
            .application(this)
            .build()

        persistenceRepository = repositoryFactory.create(RepositoryFactory.Type.DATABASE)
        networkRepository = repositoryFactory.create(RepositoryFactory.Type.NETWORK)
    }

}