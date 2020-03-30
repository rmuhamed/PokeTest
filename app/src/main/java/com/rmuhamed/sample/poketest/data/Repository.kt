package com.rmuhamed.sample.poketest.data

import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.concurrent.Future

interface Repository<T> {

    suspend fun findBy(id: Int): Pokemon

    suspend fun all(): List<T>

    suspend fun save(toBeSaved: T): Boolean

    suspend fun exists(pokemon: Pokemon): Boolean
}