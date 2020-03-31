package com.rmuhamed.sample.poketest.ui.main.adapters

import androidx.appcompat.widget.AppCompatImageView
import com.rmuhamed.sample.poketest.model.Pokemon

@FunctionalInterface
interface MyBackpackItemViewHandler {
    fun onPokemonSelected(pokemon: Pokemon, pokemonImage: AppCompatImageView)
}