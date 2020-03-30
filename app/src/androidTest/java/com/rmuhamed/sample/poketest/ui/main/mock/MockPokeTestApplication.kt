package com.rmuhamed.sample.poketest.ui.main.mock

import com.rmuhamed.sample.poketest.TemplatePokeTestApplication
import com.rmuhamed.sample.poketest.config.AppConfiguration
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.concurrent.Executors

class MockPokeTestApplication : TemplatePokeTestApplication() {

    override val repository: Repository<Pokemon>
        get() = MockRepository()

    override fun getAppConfiguration(): AppConfiguration {
        return AppConfiguration(this, "FakeDB", "https://fake.com/api/v2/")
    }
}