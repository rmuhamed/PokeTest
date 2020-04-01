package com.rmuhamed.sample.poketest.config

import com.rmuhamed.sample.poketest.data.dto.PokemonDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeAPIDefinition {

    @GET("pokemon/{id}")
    suspend fun fetchBy(@Path("id") id: Int): PokemonDTO
}
