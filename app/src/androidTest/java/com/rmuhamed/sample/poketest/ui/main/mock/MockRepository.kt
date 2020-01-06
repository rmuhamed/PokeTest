package com.rmuhamed.sample.poketest.ui.main.mock

import com.rmuhamed.sample.poketest.data.IRepository
import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class MockRepository(private val executorService: ExecutorService) : IRepository<Pokemon> {
    override fun findBy(id: String?): Future<Pokemon> {
        return executorService.submit(Callable {
            Pokemon.Builder()
                .setName("Fake")
                .setPicture("")
                .build()
        })
    }

    override fun exists(id: String?): Future<Boolean> {
        return executorService.submit(Callable { false })
    }
}