package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.ViewState
import kotlinx.coroutines.launch

class MyBackpackViewModel(private val repository: Repository<Pokemon>) : ViewModel() {
    private var _inBackpack = MutableLiveData<List<Pokemon>>()
    private var _state = MutableLiveData<ViewState>()

    val state: LiveData<ViewState>
        get() = _state

    val inMyBackpack: LiveData<List<Pokemon>>
        get() = _inBackpack

    fun allInBackpack() {
        viewModelScope.launch {
            _state.value = ViewState.LOADING
            _inBackpack.value = repository.all()
            _state.value = ViewState.LOADED
        }
    }
}
