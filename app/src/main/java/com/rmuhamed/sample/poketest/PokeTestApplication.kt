package com.rmuhamed.sample.poketest

import com.rmuhamed.sample.poketest.config.AppConfiguration
import com.rmuhamed.sample.poketest.data.PokemonRepository
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon

class PokeTestApplication : TemplatePokeTestApplication() {

    override val repository: Repository<Pokemon> by lazy {
        PokemonRepository(configuration.appDatabase.pokemonDAO(), configuration.apiDefinition)
    }

    override fun getAppConfiguration(): AppConfiguration =
        AppConfiguration(this, BuildConfig.DB_NAME, BuildConfig.BASE_API_URL);
}