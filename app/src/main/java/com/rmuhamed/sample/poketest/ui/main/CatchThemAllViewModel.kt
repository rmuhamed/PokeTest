package com.rmuhamed.sample.poketest.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.util.inBetween
import kotlinx.coroutines.launch
import java.util.*

open class CatchThemAllViewModel(
    private val repository: Repository<Pokemon>
) : ViewModel() {
    private var _caughtPokemon = MutableLiveData<Boolean>()
    private var _canWeCatchIt = MutableLiveData<Boolean>()
    private var _pokemon = MutableLiveData<Pokemon>()

    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    val caughtPokemon: LiveData<Boolean>
        get() = _caughtPokemon

    val canWeCatchIt: LiveData<Boolean>
        get() = _canWeCatchIt

    fun catchPokemon(pokemon: Pokemon) {
        pokemon.capturedAt = Date()

        viewModelScope.launch {
            val succeed = repository.save(pokemon)
            _caughtPokemon.value = succeed
        }
    }

    fun letsFindAnyPokemon() {
        viewModelScope.launch {
            val pokemon = repository.findBy(inBetween(1, 250))
            _pokemon.value = pokemon
        }
    }

    fun checkIfPokemonIsInBackpack(pokemon: Pokemon) {
         viewModelScope.launch {
            val inBackpack = repository.exists(pokemon)
             _canWeCatchIt.value = !inBackpack
        }
    }
}

