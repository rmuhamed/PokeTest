package com.rmuhamed.sample.poketest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Error
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.main.CatchThemAllViewModel

class CustomViewModelProvider(private val repository: IRepository<Pokemon, Error>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatchThemAllViewModel(repository) as T
    }
}