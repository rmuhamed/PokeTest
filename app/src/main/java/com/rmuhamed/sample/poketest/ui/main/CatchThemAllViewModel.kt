package com.rmuhamed.sample.poketest.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmuhamed.sample.poketest.config.RestApiConfigurator
import com.rmuhamed.sample.poketest.config.RestApiDefinition
import com.rmuhamed.sample.poketest.data.AsyncResult
import com.rmuhamed.sample.poketest.data.RestApiRepository
import com.rmuhamed.sample.poketest.model.Pokemon

class CatchThemAllViewModel : ViewModel() {
    val observable = MutableLiveData<Pokemon>()

    init {
        fetch()
    }

    private fun fetch() {
        val api: RestApiDefinition =
                RestApiConfigurator.createApi("https://pokeapi.co/api/v2/")

        val asyncResult =
                object : AsyncResult<Pokemon, Error> {
                    override fun onSuccess(result: Pokemon?) {
                        Log.d("SUCCESS", "Here I am")
                        observable.value = result
                    }

                    override fun onError(error: Error?) {
                        Log.d("ERROR", ":(")
                    }

                }

        val repo = RestApiRepository(api, asyncResult);

        repo.findBy(90)
    }
}
