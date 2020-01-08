package com.rmuhamed.sample.poketest.ui.main.mock

import com.rmuhamed.sample.poketest.model.Pokemon

object MockPokemonFactory {

    fun create(id: String, name: String): Pokemon =
        Pokemon.Builder()
            .setId(id)
            .setHeight("80")
            .setWeight("80")
            .setType("Water")
            .setPicture("")
            .setName(name)
            .build()
}