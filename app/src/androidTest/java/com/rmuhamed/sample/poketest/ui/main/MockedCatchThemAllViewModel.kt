package com.rmuhamed.sample.poketest.ui.main

import com.rmuhamed.sample.poketest.model.Pokemon

class MockedCatchThemAllViewModel : AbstractCatchThemAllViewModel() {
    override fun letsFindAPokemon() {
        val fakePoke = Pokemon.Builder()
            .setId("1")
            .setBaseExperience("Unknown")
            .setHeight("100")
            .setWeight("100")
            .setType("Mocked")
            .setName("Mock Pokemon")
            .build()

        pokemonInfoObservable.postValue(fakePoke)
        catchItButtonObservable.postValue(true)
    }

    override fun catchPokemon(pokemon: Pokemon) {
        caughtPokemonObservable.postValue(true)
    }
}