package com.rmuhamed.sample.poketest.data

import com.rmuhamed.sample.poketest.config.RestApiDefinition
import com.rmuhamed.sample.poketest.data.dao.PokemonDAO
import com.rmuhamed.sample.poketest.data.mappers.Mappers
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PokemonRepository(private val dao: PokemonDAO, private val api: RestApiDefinition):
    Repository<Pokemon> {

    override suspend fun findBy(id: Int): Pokemon {
        return withContext(Dispatchers.IO) {
            Mappers.toBusinessObject(api.fetchBy(id))!!
        }
    }

    override suspend fun all(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            Mappers.toBusinessObject(dao.findAll())
        }
    }

    override suspend fun save(toBeSaved: Pokemon): Boolean {
        return withContext(Dispatchers.IO) {
            dao.save(Mappers.toDBEntity(toBeSaved))
            true
        }
    }

    override suspend fun exists(pokemon: Pokemon): Boolean {
        return withContext(Dispatchers.IO) {
            dao.countBy(pokemon.id) > 0
        }
    }
}