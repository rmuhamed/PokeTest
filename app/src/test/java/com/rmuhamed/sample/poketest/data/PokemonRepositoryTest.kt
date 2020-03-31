package com.rmuhamed.sample.poketest.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rmuhamed.sample.poketest.config.RestApiDefinition
import com.rmuhamed.sample.poketest.data.dao.PokemonDAO
import com.rmuhamed.sample.poketest.data.dao.PokemonEntity
import com.rmuhamed.sample.poketest.data.dto.PokemonDTO
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

class PokemonRepositoryTest {
    private val apiDefinition: RestApiDefinition = mock()

    private val dao: PokemonDAO = mock()
    private lateinit var SUT: PokemonRepository

    @Before
    fun setUp() {
        SUT = PokemonRepository(dao, apiDefinition)
    }

    @Test
    fun test_FindBy() {
        val types = PokemonDTO.Types().also {
            it.type = PokemonDTO.Type().also { type ->
                type.name = "type" }
        }

        val fakeSprites = PokemonDTO.Sprites().also {
            it.front = ""
        }

        val fakeDTO = PokemonDTO().also {
            it.id = 1
            it.types = mutableListOf(types)
            it.sprites = fakeSprites
        }

        runBlocking {
            whenever(apiDefinition.fetchBy(any())).thenReturn(fakeDTO)
        }

        val pokemon = runBlocking {
            SUT.findBy(1)
        }

        assertTrue(pokemon.id == 1)
    }

    @Test
    fun test_GetAll() {
        val fakeEntityList = mutableListOf(PokemonEntity())
        fakeEntityList[0].apply {
            id = 1
            type = "Water"
        }

        runBlocking {
            whenever(dao.findAll()).thenReturn(fakeEntityList)
        }

        val pokemonList = runBlocking {
            SUT.all()
        }

        assertTrue(pokemonList.isNotEmpty())
    }
}