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
)
    : ViewModel(), AsyncResult<Pokemon, Error> {
    val observable = MutableLiveData<Pokemon>()

    init {
        bindToDataSource()
        fetch((1..150).shuffled().first())
    }

    private fun bindToDataSource() {
        networkRepository.addObserver(this)
    }

    private fun fetch(id: Int) {
        networkRepository.findBy(id)
    }

    fun haveItInBackpack(id: String) {
        persistenceRepository.exists(id)
    }

    override fun onSuccess(result: Pokemon?) {
        observable.postValue(result)
    }

    override fun onError(error: Error?) {
        //TODO: RM - Make a wrapper UI object instead of entity (both success and error)
    }
}
