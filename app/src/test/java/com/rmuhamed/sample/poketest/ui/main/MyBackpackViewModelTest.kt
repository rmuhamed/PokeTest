package com.rmuhamed.sample.poketest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rmuhamed.sample.poketest.CoroutineTestRule
import com.rmuhamed.sample.poketest.data.PokemonRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MyBackpackViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val fakePokemon = Pokemon.Builder()
        .setId(1)
        .setName("Pikachu")
        .build()

    private val mockedRepo = Mockito.mock(PokemonRepository::class.java)

    private lateinit var viewModel: MyBackpackViewModel

    @Before
    fun setUp() {
        viewModel = MyBackpackViewModel(mockedRepo)
    }

    @Test
    fun should_retrieve_all_pokemons_in_backpack_if_it_have_one_or_more() {
        //given
        coroutineTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(mockedRepo.all()).thenReturn(listOf(fakePokemon))
        }
        //when
        viewModel.allInBackpack()
        //then
        assertNotNull(viewModel.pokemonsInMyBackpack.value)
        assertTrue(viewModel.pokemonsInMyBackpack.value!!.isNotEmpty())
    }

    @Test
    fun should_return_empty_if_backpack_does_not_have_any_pokemon() {
        //given
        coroutineTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(mockedRepo.all()).thenReturn(emptyList())
        }
        //when
        viewModel.allInBackpack()
        //then
        assertNotNull(viewModel.pokemonsInMyBackpack.value)
        assertTrue(viewModel.pokemonsInMyBackpack.value!!.isEmpty())
    }
}