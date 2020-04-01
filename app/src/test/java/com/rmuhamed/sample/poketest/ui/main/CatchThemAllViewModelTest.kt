package com.rmuhamed.sample.poketest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rmuhamed.sample.poketest.CoroutineTestRule
import com.rmuhamed.sample.poketest.data.PokemonRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class CatchThemAllViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val mockedRepository = Mockito.mock(PokemonRepository::class.java)

    private lateinit var viewModel: CatchThemAllViewModel

    private val fakePokemon = Pokemon.Builder().setId(1).build()

    @Before
    fun setUp() {
        viewModel = Mockito.spy(CatchThemAllViewModel(mockedRepository))
    }

    @Test
    fun should_have_a_value_if_search_a_pokemon() {
        //given
        coroutineTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(mockedRepository.findBy(anyInt())).thenReturn(fakePokemon)
        }
        //when
        viewModel.letsFindAnyPokemon()
        //then
        assertEquals(fakePokemon.id, viewModel.pokemonObservable.value!!.id )
    }

    @Test
    fun should_get_true_if_Pokemon_was_caught() {
        //given
        Mockito.`when`(viewModel.pokemon).thenReturn(fakePokemon)
        coroutineTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(mockedRepository.save(fakePokemon)).thenReturn(true)
        }
        //when
        viewModel.catchPokemon()
        //then
        assertTrue(viewModel.caughtPokemon.value!!)
    }

    @Test
    fun should_get_not_possible_to_catch_if_the_kind_of_pokemon_is_in_the_backpack() {
        //given
        Mockito.`when`(viewModel.pokemon).thenReturn(fakePokemon)
        coroutineTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(mockedRepository.exists(fakePokemon)).thenReturn(true)
        }
        //when
        viewModel.checkIfPokemonIsInBackpack()
        //then
        assertFalse(viewModel.canWeCatchIt.value!!)
    }

    @Test
    fun should_possible_to_catch_if_the_kind_of_pokemon_is_not_in_the_backpack() {
        //given
        Mockito.`when`(viewModel.pokemon).thenReturn(fakePokemon)
        coroutineTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(mockedRepository.exists(fakePokemon)).thenReturn(false)
        }
        //when
        viewModel.checkIfPokemonIsInBackpack()
        //then
        assertTrue(viewModel.canWeCatchIt.value!!)
    }
}