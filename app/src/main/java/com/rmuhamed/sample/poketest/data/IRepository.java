package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.model.Pokemon;

import java.util.List;
import java.util.concurrent.Future;

public interface IRepository<T> {

    Future<Pokemon> findBy(String id);

    default Future<List<T>> getAll() {
        return null;
    }

    default Future<Boolean> save(T toBeSaved) {
        return null;
    }

    default Future<Boolean> exists(String id) {
        return null;
    }
}
