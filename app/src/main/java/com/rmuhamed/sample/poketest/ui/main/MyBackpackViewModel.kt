package com.rmuhamed.sample.poketest.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon

class MyBackpackViewModel(private val persistenceRepository: IRepository<Pokemon>) : ViewModel() {
    val myPokemonsObservable = MutableLiveData<List<Pokemon>>()

    init {
        getAllFromBackpack()
    }

    @VisibleForTesting
    internal fun getAllFromBackpack() {
        myPokemonsObservable.postValue(persistenceRepository.all.get())
    }
}
