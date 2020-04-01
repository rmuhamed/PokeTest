package com.rmuhamed.sample.poketest.data

import com.rmuhamed.sample.poketest.config.PokeAPIDefinition
import com.rmuhamed.sample.poketest.data.dao.PokemonDAO
import com.rmuhamed.sample.poketest.data.mappers.Mappers.toBusinessObject
import com.rmuhamed.sample.poketest.data.mappers.Mappers.toDBEntity
import com.rmuhamed.sample.poketest.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(private val dao: PokemonDAO, private val api: PokeAPIDefinition):
    Repository<Pokemon> {

    override suspend fun findBy(id: Int): Pokemon {
        return withContext(Dispatchers.IO) {
            val dto = api.fetchBy(id)
            toBusinessObject(dto)
        }
    }

    override suspend fun all(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            dao.findAll().map(::toBusinessObject)
        }
    }

    override suspend fun save(toBeSaved: Pokemon): Boolean {
        return withContext(Dispatchers.IO) {
            val dbEntity = toDBEntity(toBeSaved)
            dao.save(dbEntity)
            true
        }
    }

    override suspend fun exists(pokemon: Pokemon): Boolean {
        return withContext(Dispatchers.IO) {
            dao.countBy(pokemon.id) > 0
        }
    }
}