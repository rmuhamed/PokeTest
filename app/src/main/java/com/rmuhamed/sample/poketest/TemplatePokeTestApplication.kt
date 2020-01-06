package com.rmuhamed.sample.poketest

import android.app.Application
import com.rmuhamed.sample.poketest.config.AppConfiguration
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon

abstract class TemplatePokeTestApplication : Application() {
    lateinit var configuration: AppConfiguration

    abstract val persistenceRepository: IRepository<Pokemon>
    abstract val networkRepository: IRepository<Pokemon>

    override fun onCreate() {
        super.onCreate()

        configuration = getAppConfiguration()
    }

    abstract fun getAppConfiguration(): AppConfiguration
}