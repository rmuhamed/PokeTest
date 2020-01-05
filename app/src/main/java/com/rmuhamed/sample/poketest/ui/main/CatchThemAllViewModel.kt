package com.rmuhamed.sample.poketest.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.util.randomizeGet
import java.util.*

open class CatchThemAllViewModel(
    private val networkRepository: IRepository<Pokemon>,
    private val persistenceRepository: IRepository<Pokemon>
) : ViewModel() {
    val pokemonInfoObservable = MutableLiveData<Pokemon>()
    val catchItButtonObservable = MutableLiveData<Boolean>()
    val caughtPokemonObservable = MutableLiveData<Boolean>()

    init {
        val pokemon = letsFindAnyPokemon()

        checkIfPokemonHasBeenCaught(pokemon)
    }

    fun catchPokemon(pokemon: Pokemon) {
        pokemon.capturedAt = Date()
        caughtPokemonObservable.postValue(persistenceRepository.save(pokemon).get())
    }

    fun letsFindAnyPokemon(): Pokemon {
        val pokemon = networkRepository.findBy(randomizeGet(1, 200).toString()).get()
        pokemonInfoObservable.postValue(pokemon)

        return pokemon
    }

    @VisibleForTesting
    fun checkIfPokemonHasBeenCaught(pokemon: Pokemon) {
        val haveItInBackpack = persistenceRepository.exists(pokemon.id).get()
        catchItButtonObservable.postValue(!haveItInBackpack)
    }
}

