package com.rmuhamed.sample.poketest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.data.AsyncResult
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Error
import com.rmuhamed.sample.poketest.model.Pokemon

class CatchThemAllViewModel(
    private val networkRepository: IRepository<Pokemon, Error>,
    private val persistenceRepository: IRepository<Pokemon, Error>
) : ViewModel(), AsyncResult<Pokemon, Error> {
    private var id: Int

    val errorObservable = MutableLiveData<Error>()
    val pokemonInfoObservable = MutableLiveData<Pokemon>()
    val catchItButtonObservable = MutableLiveData<Boolean>()

    init {
        id = (1..150).shuffled().first()

        bindToDataSource()
        getPokemonInfoFrom(id)
    }

    private fun bindToDataSource() {
        networkRepository.addObserver(this)
    }

    private fun getPokemonInfoFrom(id: Int) {
        networkRepository.findBy(id)
    }

    override fun onSuccess(result: Pokemon?) {
        pokemonInfoObservable.postValue(result)
        //catchItButtonObservable.postValue(persistenceRepository.exists(id.toString()))
    }

    override fun onError(error: Error?) {
        errorObservable.postValue(error)
        catchItButtonObservable.postValue(false)
    }
}
