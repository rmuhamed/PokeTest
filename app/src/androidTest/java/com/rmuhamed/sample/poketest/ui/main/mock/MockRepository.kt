package com.rmuhamed.sample.poketest.ui.main.mock

import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class MockRepository() : Repository<Pokemon> {

    override suspend fun findBy(id: Int): Pokemon = MockPokemonFactory.create(1, "fakePokemon")

    override suspend fun all(): List<Pokemon> = getPokemonList()

    override suspend fun exists(pokemon: Pokemon): Boolean = false

    override suspend fun save(toBeSaved: Pokemon): Boolean = true

    private fun getPokemonList(): MutableList<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        IntRange(1, 10).forEach { pokemonId ->
            pokemonList.add(MockPokemonFactory.create(pokemonId, "Pokemon$pokemonId"))
        }
        return pokemonList
    }
}