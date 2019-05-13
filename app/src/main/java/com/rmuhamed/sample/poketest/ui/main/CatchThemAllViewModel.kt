package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.data.AsyncResult
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Error
import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.*

class CatchThemAllViewModel(
    private val networkRepository: IRepository<Pokemon, Error>,
    private val persistenceRepository: IRepository<Pokemon, Error>
) : ViewModel() {

    val errorObservable = MutableLiveData<Error>()
    val pokemonInfoObservable = MutableLiveData<Pokemon>()
    val catchItButtonObservable = MutableLiveData<Boolean>()
    val caughtPokemonObservable = MutableLiveData<Boolean>()

    init {
        bindToDataSource()
        getPokemonInfoFrom((1..150).shuffled().first())
    }

    private fun bindToDataSource() {
        networkRepository.addObserver(object : AsyncResult<Pokemon, Error> {
            override fun onError(error: Error?) {
                catchItButtonObservable.postValue(false)
                errorObservable.postValue(error)
            }

            override fun onSuccess(result: Pokemon?) {
                pokemonInfoObservable.postValue(result)
                persistenceRepository.exists(result?.id.toString())
            }
        })
        persistenceRepository.addObserver(object : AsyncResult<Pokemon, Error> {
            override fun onError(error: Error) {
                errorObservable.postValue(error)
            }

            override fun onPresent(present: Boolean) {
                catchItButtonObservable.postValue(!present)
            }

            override fun onSuccess(result: Pokemon?) {
                caughtPokemonObservable.postValue(true)
            }
        })
    }

    fun catchPokemon(pokemon: Pokemon) {
        pokemon.capturedAt = Date()
        persistenceRepository.save(pokemon)
    }

    private fun getPokemonInfoFrom(id: Int) {
        networkRepository.findBy(id)
    }

    fun searchForAnotherPokemon() {
        getPokemonInfoFrom((1..150).shuffled().first())
    }
}
