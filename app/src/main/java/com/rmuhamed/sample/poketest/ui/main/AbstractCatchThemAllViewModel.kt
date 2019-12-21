package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.model.Pokemon

abstract class AbstractCatchThemAllViewModel() : ViewModel() {
    val pokemonInfoObservable = MutableLiveData<Pokemon>()
    val catchItButtonObservable = MutableLiveData<Boolean>()
    val caughtPokemonObservable = MutableLiveData<Boolean>()

    abstract fun letsFindAPokemon()

    abstract fun catchPokemon(pokemon: Pokemon)
}