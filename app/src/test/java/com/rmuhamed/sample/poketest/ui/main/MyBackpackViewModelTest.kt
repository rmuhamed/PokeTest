package com.rmuhamed.sample.poketest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rmuhamed.sample.poketest.CoroutineTestRule
import com.rmuhamed.sample.poketest.data.Repository
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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

    private val mockedRepo = mock<Repository<Pokemon>>()

    private lateinit var viewModel: MyBackpackViewModel

    @Before
    fun setUp() {
        viewModel = MyBackpackViewModel(mockedRepo)
    }

    @Test
    fun test_GetMyPokemonsObservable() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(mockedRepo.all()).thenReturn(listOf(fakePokemon))
        }

        viewModel.allInBackpack()

        assertNotNull(viewModel.pokemonsInMyBackpack.value)
        assertTrue(viewModel.pokemonsInMyBackpack.value!!.isNotEmpty())
    }
}