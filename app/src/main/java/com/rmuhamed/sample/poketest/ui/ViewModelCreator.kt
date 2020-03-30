package com.rmuhamed.sample.poketest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.main.CatchThemAllViewModel
import com.rmuhamed.sample.poketest.ui.main.MyBackpackViewModel

class ViewModelCreator(
    private val repository: Repository<Pokemon>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel
        if (modelClass == CatchThemAllViewModel::class.java) {
            viewModel = CatchThemAllViewModel(repository)
        } else {
            viewModel = MyBackpackViewModel(repository)
        }

        return viewModel as T
    }
}