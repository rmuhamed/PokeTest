package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.data.AsyncResult
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Error
import com.rmuhamed.sample.poketest.model.Pokemon

class MyBackpackViewModel(private val persistenceRepository: IRepository<Pokemon, Error>) : ViewModel() {
    val errorObservable = MutableLiveData<Error>()
    val myPokemonsObservable = MutableLiveData<List<Pokemon>>()

    init {
        bindToDataSource()
        getAllFromBackpack()
    }

    private fun getAllFromBackpack() {
        persistenceRepository.getAll()
    }

    private fun bindToDataSource() {
        persistenceRepository.addObserver(object : AsyncResult<Pokemon, Error> {
            override fun onError(error: Error) {
                errorObservable.postValue(error)
            }

            override fun onSuccess(result: List<Pokemon>?) {
                myPokemonsObservable.postValue(result)
            }
        })
    }
}
