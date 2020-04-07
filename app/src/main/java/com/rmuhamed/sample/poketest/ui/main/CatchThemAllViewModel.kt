package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.ViewState
import com.rmuhamed.sample.poketest.util.inBetween
import kotlinx.coroutines.launch
import java.util.*

class CatchThemAllViewModel(private val repository: Repository<Pokemon>) : ViewModel() {
    private var _caughtPokemon = MutableLiveData<Boolean>()
    private var _canWeCatchIt = MutableLiveData<Boolean>()
    private var _pokemon = MutableLiveData<Pokemon>()
    private var _viewState = MutableLiveData<ViewState>()

    val pokemon: Pokemon?
        get() = pokemonObservable.value

    val pokemonObservable: LiveData<Pokemon>
        get() = _pokemon

    val caughtPokemon: LiveData<Boolean>
        get() = _caughtPokemon

    val canWeCatchIt: LiveData<Boolean>
        get() = _canWeCatchIt

    val state: LiveData<ViewState>
        get() = _viewState

    fun catchPokemon() {
        pokemon?.let {
            it.capturedAt = Date()

            viewModelScope.launch {
                val succeed = repository.save(it)
                _caughtPokemon.value = succeed
            }
        } ?: run {
            _viewState.value = ViewState.ERROR
        }
    }

    fun letsFindAnyPokemon() {
        viewModelScope.launch() {
            _viewState.value = ViewState.LOADING
            val pokemon = repository.findBy(inBetween(1, 250))
            _pokemon.value = pokemon
            _viewState.value = ViewState.LOADED
        }
    }

    fun checkIfPokemonIsInBackpack() {
        pokemon?.let {
            viewModelScope.launch {
                val inBackpack = repository.exists(it)
                _canWeCatchIt.value = !inBackpack
            }
        } ?: run {
            _viewState.value = ViewState.ERROR
        }
    }
}

