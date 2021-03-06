package com.rmuhamed.sample.poketest.config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApiConfigurator {
    private var retrofit: Retrofit? = null

    private fun configure(baseUrl: String): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @JvmStatic
    fun createApi(baseUrl: String): PokeAPIDefinition {
        if (retrofit == null) {
            retrofit = this.configure(baseUrl)
        }

        return retrofit!!.create(PokeAPIDefinition::class.java)
    }
}
