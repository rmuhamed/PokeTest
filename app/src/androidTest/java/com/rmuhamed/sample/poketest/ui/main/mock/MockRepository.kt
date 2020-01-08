package com.rmuhamed.sample.poketest.ui.main.mock

import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class MockRepository(private val executorService: ExecutorService) : IRepository<Pokemon> {


    override fun findBy(id: String?): Future<Pokemon> {
        return executorService.submit(Callable {
            getSinglePokemon("1", "fake")
        })
    }

    override fun getAll(): Future<MutableList<Pokemon>> {
        return executorService.submit(Callable {
            getPokemonList()
        })
    }

    override fun exists(id: String?): Future<Boolean> {
        return executorService.submit(Callable { false })
    }

    private fun getPokemonList(): MutableList<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        IntRange(1,10).forEach {
            pokemonList.add(getSinglePokemon(it.toString(), "Fake$it"))
        }
        return pokemonList
    }

    private fun getSinglePokemon(id: String, name: String): Pokemon =
        Pokemon.Builder()
            .setName(name)
            .setId(id)
            .setPicture("")
            .build()
}