package com.rmuhamed.sample.poketest.ui.main

import com.nhaarman.mockitokotlin2.*
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.internal.verification.Times
import java.util.concurrent.Future

class CatchThemAllViewModelTest {
    @Mock
    val mockedRepo = mock<IRepository<Pokemon>>()

    lateinit var viewModel: CatchThemAllViewModel

    @Before
    fun setUp() {
        val poke = Pokemon.Builder().setId("1").build()

        val futureExists = mock<Future<Boolean>> {
            on { get() } doReturn false
        }

        val findByFuture = mock<Future<Pokemon>> {
            on { get() } doReturn poke
        }

        whenever(mockedRepo.exists(any())).thenReturn(futureExists)
        whenever(mockedRepo.findBy(any())).thenReturn(findByFuture)

        viewModel = CatchThemAllViewModel(mockedRepo, mockedRepo)
    }

    @Test
    fun test_PokemonInfoObservable() {
        assertNotNull(viewModel.pokemonInfoObservable)
    }

    @Test
    fun test_CatchItButtonObservable() {
        assertNotNull(viewModel.catchItButtonObservable)
    }

    @Test
    fun test_CaughtPokemonObservable() {
        assertNotNull(viewModel.caughtPokemonObservable)
    }

    @Test
    fun test_CatchPokemon() {
        val result = mock<Future<Boolean>> {
            on { get() } doReturn true
        }

        whenever(mockedRepo.save(any())).thenReturn(result)
        val somePoke = Pokemon.Builder().setId("1").build()
        viewModel.catchPokemon(somePoke)

        verify(mockedRepo).save(somePoke)
    }

    @Test
    fun test_LetsFindPokemon() {
        val resultPoke = mock<Future<Pokemon>> {
            on { get() } doReturn Pokemon.Builder().setId("1").build()
        }
        whenever(mockedRepo.findBy(any())).thenReturn(resultPoke)

        viewModel.letsFindPokemon()

        verify(mockedRepo, Times(2)).findBy(any())
    }
}