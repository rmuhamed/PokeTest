package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.util.randomizeGet
import java.util.*

class CatchThemAllViewModel(
    private val networkRepository: IRepository<Pokemon>,
    private val persistenceRepository: IRepository<Pokemon>
) : ViewModel() {

    val pokemonInfoObservable = MutableLiveData<Pokemon>()
    val catchItButtonObservable = MutableLiveData<Boolean>()
    val caughtPokemonObservable = MutableLiveData<Boolean>()

    init {
        letsFindPokemon()
    }

    fun catchPokemon(pokemon: Pokemon) {
        pokemon.capturedAt = Date()
        caughtPokemonObservable.postValue(persistenceRepository.save(pokemon).get())
    }

    fun letsFindPokemon() {
        getPokemonInfoFrom(randomizeGet(1, 200).toString())
    }

    private fun getPokemonInfoFrom(id: String) {
        val pokemon = networkRepository.findBy(id).get()
        pokemonInfoObservable.postValue(pokemon)

        val haveItInBackpack = persistenceRepository.exists(pokemon.id).get()
        catchItButtonObservable.postValue(!haveItInBackpack)
    }
}

