package com.rmuhamed.sample.poketest.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RestApiConfigurator private constructor() {

    companion object {
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

        fun createApi(baseUrl: String): RestApiDefinition {
            if (retrofit == null) {
                retrofit = this.configure(baseUrl)
            }

            return retrofit!!.create(RestApiDefinition::class.java)
        }

        private var retrofit: Retrofit? = null
    }
}
