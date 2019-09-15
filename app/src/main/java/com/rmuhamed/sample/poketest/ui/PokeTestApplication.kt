package com.rmuhamed.sample.poketest.ui

import android.app.Application
import com.rmuhamed.sample.poketest.BuildConfig
import com.rmuhamed.sample.poketest.config.AppConfiguration

class PokeTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppConfiguration.configure(this, BuildConfig.DB_NAME, BuildConfig.BASE_API_URL);
    }

}