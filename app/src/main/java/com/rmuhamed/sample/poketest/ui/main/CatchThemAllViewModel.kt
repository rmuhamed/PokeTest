package com.rmuhamed.sample.poketest.ui.main

import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.util.randomizeGet
import java.util.*

open class CatchThemAllViewModel(
    private val networkRepository: IRepository<Pokemon>,
    private val persistenceRepository: IRepository<Pokemon>
) : AbstractCatchThemAllViewModel() {

    override fun catchPokemon(pokemon: Pokemon) {
        pokemon.capturedAt = Date()
        caughtPokemonObservable.postValue(persistenceRepository.save(pokemon).get())
    }

    override fun letsFindAPokemon() {
        getPokemonInfoFrom(randomizeGet(1, 200).toString())
    }

    private fun getPokemonInfoFrom(id: String) {
        val pokemon = networkRepository.findBy(id).get()
        pokemonInfoObservable.postValue(pokemon)

        val haveItInBackpack = persistenceRepository.exists(pokemon.id).get()
        catchItButtonObservable.postValue(!haveItInBackpack)
    }
}

