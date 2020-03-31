package com.rmuhamed.sample.poketest.data.mappers

import com.rmuhamed.sample.poketest.data.dao.PokemonEntity
import com.rmuhamed.sample.poketest.data.dto.PokemonDTO
import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.*

object Mappers {
    @JvmStatic
    infix fun toDBEntity(from: Pokemon): PokemonEntity {
        val entity = PokemonEntity()
        entity.id = from.id
        entity.name = from.name
        entity.type = from.type
        entity.baseExperience = from.baseExperience
        entity.height = from.height
        entity.weight = from.weight
        entity.picture = from.picture
        entity.capturedAt = from.capturedAt.time

        return entity
    }

    @JvmStatic
    infix fun toBusinessObject(from: PokemonEntity): Pokemon {
        val pokemon = Pokemon.Builder()
            .setId(from.id)
            .setType(from.type)
            .setName(from.name)
            .setBaseExperience(from.baseExperience)
            .setHeight(from.height)
            .setWeight(from.weight)
            .setPicture(from.picture)
            .build()
        pokemon.capturedAt = Date(from.capturedAt)

        return pokemon
    }

    @JvmStatic
    infix fun toBusinessObject(from: PokemonDTO): Pokemon =
        Pokemon.Builder()
            .setId(from.id)
            .setHeight(from.height)
            .setName(from.name)
            .setWeight(from.weight)
            .setBaseExperience(from.baseExperience)
            .setType(from.types[0].type.name)
            .setPicture(from.sprites.front)
            .build()

    @JvmStatic
    fun toBusinessObject(entities: List<PokemonEntity>): List<Pokemon> {
        return entities.map(::toBusinessObject)
    }
}
