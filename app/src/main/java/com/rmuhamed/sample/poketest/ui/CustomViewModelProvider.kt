package com.rmuhamed.sample.poketest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.main.CatchThemAllViewModel
import com.rmuhamed.sample.poketest.ui.main.MyBackpackViewModel

class CustomViewModelProvider(
    private val networkRepository: IRepository<Pokemon>,
    private val persistenceRepository: IRepository<Pokemon>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel
        if (modelClass == CatchThemAllViewModel::class.java) {
            viewModel = CatchThemAllViewModel(networkRepository, persistenceRepository)
        } else {
            viewModel = MyBackpackViewModel(persistenceRepository)
        }

        return viewModel as T
    }
}