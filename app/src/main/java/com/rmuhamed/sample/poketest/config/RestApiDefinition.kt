package com.rmuhamed.sample.poketest.config

import com.rmuhamed.sample.poketest.data.dto.PokemonDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiDefinition {

    @GET("pokemon/{id}")
    fun fetchBy(@Path("id") id: Int?): Call<PokemonDTO>
}
