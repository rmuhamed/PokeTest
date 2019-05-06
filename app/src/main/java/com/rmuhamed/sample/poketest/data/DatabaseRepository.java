package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.model.Pokemon;

public class DatabaseRepository<T> implements IRepository<Pokemon, Error> {

    public DatabaseRepository( ) {
    }


    @Override
    public AsyncResult<Pokemon, Error> findBy(Integer id) {
        return null;
    }

    @Override
    public AsyncResult<Pokemon, Error> getAll() {
        return null;
    }

    @Override
    public Boolean save(Pokemon toBeSaved) {
        return true;
    }
}
