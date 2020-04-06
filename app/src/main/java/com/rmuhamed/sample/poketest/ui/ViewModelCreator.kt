package com.rmuhamed.sample.poketest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import com.rmuhamed.sample.poketest.ui.main.CatchThemAllViewModel
import com.rmuhamed.sample.poketest.ui.main.MyBackpackViewModel

class ViewModelCreator(private val repository: Repository<Pokemon>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when (modelClass) {
            CatchThemAllViewModel::class.java -> CatchThemAllViewModel(repository) as T
            MyBackpackViewModel::class.java -> MyBackpackViewModel(repository) as T
            else -> throw ClassNotFoundException("ViewModel wasn't found")
        }
}