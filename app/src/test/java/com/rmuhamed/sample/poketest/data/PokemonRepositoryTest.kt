package com.rmuhamed.sample.poketest.data

import com.rmuhamed.sample.poketest.config.RestApiDefinition
import com.rmuhamed.sample.poketest.data.dao.PokemonDAO
import com.rmuhamed.sample.poketest.data.dao.PokemonEntity
import com.rmuhamed.sample.poketest.data.dto.PokemonDTO
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito

class PokemonRepositoryTest {
    private val apiDefinition = Mockito.mock(RestApiDefinition::class.java)

    private val dao = Mockito.mock(PokemonDAO::class.java)
    private lateinit var SUT: PokemonRepository

    @Before
    fun setUp() {
        SUT = PokemonRepository(dao, apiDefinition)
    }

    @Test
    fun check_findBy() {
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
            Mockito.`when`(apiDefinition.fetchBy(anyInt())).thenReturn(fakeDTO)
        }

        val pokemon = runBlocking {
            SUT.findBy(1)
        }

        assertTrue(pokemon.id == 1)
    }

    @Test
    fun check_getAll() {
        val fakeEntityList = mutableListOf(PokemonEntity())
        fakeEntityList[0].apply {
            id = 1
            type = "Water"
        }

        runBlocking {
            Mockito.`when`(dao.findAll()).thenReturn(fakeEntityList)
        }

        val pokemonList = runBlocking {
            SUT.all()
        }

        assertTrue(pokemonList.isNotEmpty())
    }
}