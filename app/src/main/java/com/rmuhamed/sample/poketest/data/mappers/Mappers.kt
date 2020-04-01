package com.rmuhamed.sample.poketest.data.mappers

import com.rmuhamed.sample.poketest.data.dao.PokemonEntity
import com.rmuhamed.sample.poketest.data.dto.PokemonDTO
import com.rmuhamed.sample.poketest.model.Pokemon
import java.util.*

object Mappers {

    fun toDBEntity(from: Pokemon): PokemonEntity =
        PokemonEntity().also {
            it.id = from.id
            it.name = from.name
            it.type = from.type
            it.baseExperience = from.baseExperience
            it.height = from.height
            it.weight = from.weight
            it.picture = from.picture
            it.capturedAt = from.capturedAt.time
    }

    fun toBusinessObject(from: PokemonEntity): Pokemon {
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

    fun toBusinessObject(from: PokemonDTO): Pokemon =
        Pokemon.Builder()
            .setId(from.id)
            .setHeight(from.height)
            .setName(from.name)
            .setWeight(from.weight)
            .setBaseExperience(from.baseExperience)
            .setType(from.types[0].type.name)
            .setPicture(from.sprites.front)
            .build()
}
