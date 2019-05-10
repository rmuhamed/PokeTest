package com.rmuhamed.sample.poketest.data;

import androidx.annotation.NonNull;
import com.rmuhamed.sample.poketest.data.dao.PokemonDAO;
import com.rmuhamed.sample.poketest.data.dao.PokemonEntity;
import com.rmuhamed.sample.poketest.model.Error;
import com.rmuhamed.sample.poketest.model.Pokemon;

public class DatabaseRepository implements IRepository<Pokemon, Error> {

    private final PokemonDAO dao;

    public DatabaseRepository(PokemonDAO dao) {
        this.dao = dao;
    }

    @Override
    public Boolean save(Pokemon toBeSaved) {
        this.dao.save(Mapper.convertFrom(toBeSaved));
        return true;
    }

    @Override
    public Boolean exists(String id) {
        return this.dao.countBy(id) > 0;
    }

    @Override
    public void addObserver(AsyncResult<Pokemon, Error> observer) { }

    static class Mapper {
        static PokemonEntity convertFrom(@NonNull Pokemon aPokemon) {
            PokemonEntity entity = new PokemonEntity();
            entity.setId(aPokemon.getId());
            entity.setName(aPokemon.getName());
            entity.setBaseExperience(aPokemon.getBaseExperience());
            entity.setHeight(aPokemon.getHeight());
            entity.setWeight(aPokemon.getWeight());
            entity.setPicture(aPokemon.getPicture());
            entity.setCapturedAt(aPokemon.getCapturedAt().getTime());

            return entity;
        }
    }
}
