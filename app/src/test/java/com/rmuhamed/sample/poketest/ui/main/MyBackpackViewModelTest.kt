package com.rmuhamed.sample.poketest.ui.main

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.Times
import java.util.concurrent.Future

class MyBackpackViewModelTest {

    @Mock
    val mockedFuture = mock<Future<List<Pokemon>>> {
        on { get() } doReturn emptyList()
    }

    @Mock
    val mockedRepo = mock<IRepository<Pokemon>> {
        on { all } doReturn mockedFuture
    }

    lateinit var viewModel: MyBackpackViewModel

    @Before
    fun setUp() {
        viewModel = MyBackpackViewModel(mockedRepo)
    }

    @Test
    fun test_GetMyPokemonsObservable() {
        Mockito.verify(mockedRepo, Times(1)).all

        assertNotNull(viewModel.myPokemonsObservable)
    }
}