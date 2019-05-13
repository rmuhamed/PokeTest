package com.rmuhamed.sample.poketest.ui.main.adapters

import com.rmuhamed.sample.poketest.model.Pokemon

interface MyBackpackItemViewHandler {
    fun onPokemonSelected(pokemon: Pokemon)
}