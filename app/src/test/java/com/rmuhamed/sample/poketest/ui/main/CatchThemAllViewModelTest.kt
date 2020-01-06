package com.rmuhamed.sample.poketest.ui.main

import com.nhaarman.mockitokotlin2.*
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.internal.verification.Times
import java.util.concurrent.Future

class CatchThemAllViewModelTest {
    val mockedRepo = mock<IRepository<Pokemon>>()

    lateinit var viewModel: CatchThemAllViewModel

    private val poke = Pokemon.Builder().setId("1").build()

    @Before
    fun setUp() {

        val futureExists = mock<Future<Boolean>> {
            on { get() } doReturn false
        }

        val findByFuture = mock<Future<Pokemon>> {
            on { get() } doReturn poke
        }

        val saveFuture = mock<Future<Boolean>> {
            on { get() } doReturn true
        }

        whenever(mockedRepo.exists(any())).thenReturn(futureExists)
        whenever(mockedRepo.findBy(any())).thenReturn(findByFuture)
        whenever(mockedRepo.save(any())).thenReturn(saveFuture)

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
    fun test_LetsFindAnyPokemon() {
        viewModel.letsFindAnyPokemon()

        //One at init, the second because we are explicitly calling the method
        verify(mockedRepo, times(2)).findBy(any())
    }

    @Test
    fun test_CatchPokemon() {
        viewModel.catchPokemon(poke)

        verify(mockedRepo, Times(1)).save(poke)
    }

    @Test
    fun test_checkIfPokemonHasBeenCaught() {
        viewModel.checkIfPokemonHasBeenCaught(poke)


        //One at init, the second because we are explicitly calling the method
        verify(mockedRepo, Times(2)).exists(poke.id)
    }
}