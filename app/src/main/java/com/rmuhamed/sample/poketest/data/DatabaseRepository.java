package com.rmuhamed.sample.poketest.data;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.rmuhamed.sample.poketest.data.dao.PokemonDAO;
import com.rmuhamed.sample.poketest.data.dao.PokemonEntity;
import com.rmuhamed.sample.poketest.model.Error;
import com.rmuhamed.sample.poketest.model.Pokemon;

//TODO: RM - Think about moving ioThread background stuff to WorkManager here
public class DatabaseRepository implements IRepository<Pokemon, Error> {

    private final PokemonDAO dao;
    private AsyncResult<Pokemon, Error> observer;

    DatabaseRepository(PokemonDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(Pokemon toBeSaved) {
        new AsyncTask<Pokemon, Void, Pokemon>() {
            @Override
            protected Pokemon doInBackground(Pokemon... pokemons) {
                PokemonEntity entity = Mapper.convertFrom(pokemons[0]);
                dao.save(entity);
                return pokemons[0];
            }

            @Override
            protected void onPostExecute(Pokemon aSavedPokemon) {
                observer.onSuccess(aSavedPokemon);
            }
        }.execute(toBeSaved);
    }

    @Override
    public void exists(String id) {
        new AsyncTask<String, Void, Integer>() {
            @Override
            protected Integer doInBackground(String... ids) {
                return dao.countBy(ids[0]);
            }

            @Override
            protected void onPostExecute(Integer count) {
                observer.onPresent(count > 0);
            }
        }.execute(id);
    }

    @Override
    public void addObserver(AsyncResult<Pokemon, Error> observer) {
        this.observer = observer;
    }

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
