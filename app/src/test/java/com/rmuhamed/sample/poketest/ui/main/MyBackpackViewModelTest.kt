package com.rmuhamed.sample.poketest.ui.main

import com.nhaarman.mockitokotlin2.mock
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Error
import com.rmuhamed.sample.poketest.model.Pokemon
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyBackpackViewModelTest {

    @Mock
    val mockedRepo = mock<IRepository<Pokemon, Error>>()

    lateinit var viewModel: MyBackpackViewModel

    @Before
    fun setUp() {
        viewModel = MyBackpackViewModel(mockedRepo)
    }

    @Test
    fun getErrorObservable() {
        assertNotNull(viewModel.errorObservable)
    }

    @Test
    fun getMyPokemonsObservable() {
        assertNotNull(viewModel.myPokemonsObservable)
    }
}