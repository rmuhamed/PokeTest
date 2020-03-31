package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.coroutines.launch

class MyBackpackViewModel(private val repository: Repository<Pokemon>) : ViewModel() {
    private var _inBackpack = MutableLiveData<List<Pokemon>>()

    val pokemonsInMyBackpack: LiveData<List<Pokemon>>
        get() = _inBackpack

    fun allInBackpack() {
        viewModelScope.launch {
            _inBackpack.value = repository.all()
        }
    }
}
